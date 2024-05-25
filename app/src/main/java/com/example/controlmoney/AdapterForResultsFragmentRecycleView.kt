package com.example.controlmoney

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import android.view.animation.Animation.AnimationListener
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.controlmoney.databinding.ItemActivityFinanceBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.lang.Exception
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale


class AdapterForResultsFragmentRecycleView(private val listener: ListenerChangeCountMoney): RecyclerView.Adapter<AdapterForResultsFragmentRecycleView.ViewHolder>(),ItemChanges {

    private var checkBoxVisible = false
    private val listColors = listOf<Int>(
        Color.parseColor("#2A2A5F"),
        Color.parseColor("#3D5AFE"),
        Color.parseColor("#536DFE"),
        Color.parseColor("#1E88E5"),
        Color.parseColor("#1976D2"),
        Color.parseColor("#1565C0"),
        Color.parseColor("#0D47A1"),
        Color.parseColor("#42A5F5"),
        Color.parseColor("#64B5F6"),
        Color.parseColor("#90CAF9"),
        Color.parseColor("#BBDEFB"),
    )
    private var listCurrencies = DataItems().currencies


    private var mutableListFinanceItem = mutableListOf(
        DataItems.ItemFinanceParameters("Кредит", 230.0,  listColors[0], R.drawable.cafe, "RUB"),
        DataItems.ItemFinanceParameters("Коммуналка", 560.0,  listColors[1], R.drawable.home, "USD"),
        DataItems.ItemFinanceParameters("Продукты", 136.38, listColors[2], R.drawable.cinema, "RUB"),
        DataItems.ItemFinanceParameters("Подарок на 8 марта", 60.78,  listColors[3], R.drawable.delivery, "RUB"),
        DataItems.ItemFinanceParameters("Развлечение", 78.03,  listColors[4], R.drawable.cinema, "RUB"),
        DataItems.ItemFinanceParameters("Ресторан", 120.45,  listColors[5], R.drawable.cafe, "RUB"),
        DataItems.ItemFinanceParameters("Credit", 1.0,  listColors[6], R.drawable.home, "RUB"),
        DataItems.ItemFinanceParameters("Credit", 1.0,  listColors[7], R.drawable.home, "RUB"),
        DataItems.ItemFinanceParameters("Credit", 1.0,  listColors[8], R.drawable.home, "RUB"),
        DataItems.ItemFinanceParameters("Credit", 1.0,  listColors[9], R.drawable.cafe, "RUB"),
        DataItems.ItemFinanceParameters("Credit", 1.0,  listColors[10], R.drawable.home, "RUB")
    )
    class ViewHolder(item: View, private val listener: ItemChanges): RecyclerView.ViewHolder(item), ChangeAmount {

        private var binding = ItemActivityFinanceBinding.bind(item)
        private var position = 0
        private var isPlus = true
        fun bind(data: DataItems.ItemFinanceParameters, position: Int, isVisible: Boolean) {
            initAllObjects(data, isVisible)
            this.position = position
            binding.plus.setOnClickListener { changeAmount(true) }
            binding.minus.setOnClickListener { changeAmount(false) }
            binding.clear.setOnClickListener {
                val anim = AnimationUtils.loadAnimation(itemView.context, R.anim.delete_anim)
                anim.setAnimationListener(object : AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {}
                    override fun onAnimationRepeat(p0: Animation?) {}
                    override fun onAnimationEnd(p0: Animation?) {
                        listener.deleteItem(position)
                    }
                })
                itemView.startAnimation(anim)
            }
        }

        private fun initAllObjects(data: DataItems.ItemFinanceParameters, isVisible: Boolean) {
            binding.cardView.setCardBackgroundColor(data.color)
            binding.nameTV.text = data.name
            binding.iconImage.setImageResource(data.image)
            binding.iconImage.setColorFilter(data.color)
            binding.clear.isVisible = isVisible

            if (invertAmount(data.amount))
                binding.amountTV.text = data.amount.toInt().toString()
            else
                binding.amountTV.text = data.amount.toString()

            binding.amountTV.text = "${binding.amountTV.text} ${data.currency}"
        }


        private fun changeAmount(plus: Boolean) {
            isPlus = plus
            val dialog = DialogChangeAmountItem(itemView.context, this)
            dialog.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        private fun invertAmount(max: Double) = max == max.toInt().toDouble()

        override fun amountSetChanged(amountItem: Double) {
            val amount = if (isPlus) amountItem else -amountItem
            listener.changeAmount(amount, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_activity_finance, parent, false)
        return ViewHolder(view, this)
    }

    override fun getItemCount() = mutableListFinanceItem.count()


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mutableListFinanceItem[position], position, checkBoxVisible)
    }

    suspend fun currencyConvert(currency: String) {
        val client = OkHttpClient()
        val url = DataApiCurrency().url + currency
        val request = Request.Builder().url(url).build()

        withContext(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val jsonObject = JSONObject(response.body?.string()).getJSONObject("conversion_rates")
                    Log.d("my_log", jsonObject.toString())

                    val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
                        decimalSeparator = '.'
                    }
                    val decimalFormat = DecimalFormat("#.##", symbols)
                    listCurrencies.forEach {
                        try {
                            val priceString = decimalFormat.format(jsonObject.getDouble(it.nameCurrency))
                            Log.d("my_log", "Formatted price for ${it.nameCurrency}: $priceString")
                            it.prices = priceString.toDouble()
                        } catch (e: NumberFormatException) {
                            Log.e("CurrencyConversion", "Error converting formatted price to double: ${e.message}")
                        }
                    }
                } else {
                    Log.e("CurrencyConversion", "Response not successful: ${response.code}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("CurrencyConversion", "Exception during currency conversion: ${e.message}")
            }
        }
    }
    fun add(data: DataItems.ItemFinanceParameters) {
        mutableListFinanceItem.add(data)
        notifyDataSetChanged()
    }

    fun getList() = mutableListFinanceItem

    fun checkBoxVisibility() {
        checkBoxVisible = !checkBoxVisible
        notifyDataSetChanged()
    }

    override fun deleteItem(position: Int) {
        mutableListFinanceItem.removeAt(position)
        listener.dataSetChanged()
        notifyDataSetChanged()
    }

    override fun changeAmount(money: Double, position: Int) {
        mutableListFinanceItem[position].amount += money
        if (mutableListFinanceItem[position].amount < 0)
            mutableListFinanceItem[position].amount = 0.0
        listener.dataSetChanged()
        notifyDataSetChanged()
    }


    fun updateConvertCurrencies(): MutableList<DataItems.ItemFinanceParameters> {
        val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
            decimalSeparator = '.'
        }
        val decimalFormat = DecimalFormat("#.##", symbols)

        mutableListFinanceItem.forEach { item ->
            listCurrencies.forEach { currency ->
                if (item.currency == currency.nameCurrency) {
                    try {
                        val formattedValue = decimalFormat.format(item.amount / currency.prices)
                        item.convertCurrency = formattedValue.toDouble()
                    } catch (e: NumberFormatException) {
                        Log.e("CurrencyConversion", "Error converting value to number: ${e.message}")
                    }
                }
            }
        }
        return mutableListFinanceItem
    }



}
interface ItemChanges {
    fun deleteItem(position: Int)
    fun changeAmount(money: Double, position: Int)

}