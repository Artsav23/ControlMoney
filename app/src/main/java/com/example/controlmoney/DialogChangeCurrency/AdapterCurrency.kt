package com.example.controlmoney.DialogChangeCurrency

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityManager.AudioDescriptionRequestedChangeListener
import androidx.recyclerview.widget.RecyclerView
import com.example.controlmoney.R
import com.example.controlmoney.RecycleViewsForSelectStyleDialog.OnClickListenerItem
import com.example.controlmoney.databinding.ItemCurrencyBinding

class AdapterCurrency: RecyclerView.Adapter<AdapterCurrency.ViewHolder>(), OnClickListenerItem {

    private var positionChooseCurrency = 1
    private val mutableListCurrency = mutableListOf(
        CurrencyParameters("BYN"),
        CurrencyParameters("RUB", true),
        CurrencyParameters("USD"),
        CurrencyParameters("EUR"),
        CurrencyParameters("JPY"),
        CurrencyParameters("GBP"),
        CurrencyParameters("AUD"),
        CurrencyParameters("CAD"),
        CurrencyParameters("CHF"),
        CurrencyParameters("CNY"),
        CurrencyParameters("SEK"),
        CurrencyParameters("NZD"))

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemCurrencyBinding.bind(item)

        fun bind(data: CurrencyParameters, listener: OnClickListenerItem, position: Int) {
            binding.currencyTextView.text = data.currency
            if (data.chooseFlag)
                binding.cardView.setCardBackgroundColor(Color.parseColor("#373E4A"))
            else
                binding.cardView.setCardBackgroundColor(Color.parseColor("#2F343E"))
            itemView.setOnClickListener { listener.clickItem(position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mutableListCurrency.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mutableListCurrency[position], this, position)
    }
    data class CurrencyParameters (
        val currency: String,
        var chooseFlag: Boolean = false
    )

    override fun clickItem(position: Int) {
        positionChooseCurrency = position
        var num = 0
        mutableListCurrency.forEach {
            it.chooseFlag = num == position
            num++
        }
        notifyDataSetChanged()
    }

    fun getChooseCurrency(): String = mutableListCurrency[positionChooseCurrency].currency
}