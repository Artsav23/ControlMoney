package com.example.controlmoney

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.controlmoney.databinding.ItemActivityFinanceBinding


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

    private var mutableListFinanceItem = mutableListOf(
        InformationAboutItemFinance("Кредит", 230.0,  listColors[0], R.drawable.cafe, "RUB"),
        InformationAboutItemFinance("Коммуналка", 560.0,  listColors[1], R.drawable.home, "RUB"),
        InformationAboutItemFinance("Продукты", 136.38,  listColors[2], R.drawable.cinema, "RUB"),
        InformationAboutItemFinance("Подарок на 8 марта", 60.78,  listColors[3], R.drawable.delivery, "RUB"),
        InformationAboutItemFinance("Развлечение", 78.03,  listColors[4], R.drawable.cinema, "RUB"),
        InformationAboutItemFinance("Ресторан", 120.45,  listColors[5], R.drawable.cafe, "RUB"),
        InformationAboutItemFinance("Credit", 1.0,  listColors[6], R.drawable.home, "RUB"),
        InformationAboutItemFinance("Credit", 1.0,  listColors[7], R.drawable.home, "RUB"),
        InformationAboutItemFinance("Credit", 1.0,  listColors[8], R.drawable.home, "RUB"),
        InformationAboutItemFinance("Credit", 1.0,  listColors[9], R.drawable.cafe, "RUB"),
        InformationAboutItemFinance("Credit", 1.0,  listColors[10], R.drawable.home, "RUB")
    )
    class ViewHolder(item: View, private val listener: ItemChanges): RecyclerView.ViewHolder(item), ChangeAmount {

        private var binding = ItemActivityFinanceBinding.bind(item)
        private var position = 0
        private var isPlus = true
        fun bind(data: InformationAboutItemFinance, position: Int, isVisible: Boolean) {
            binding.cardView.setCardBackgroundColor(data.color)
            binding.plus.setTextColor(data.color)
            binding.minus.setTextColor(data.color)
            binding.nameTV.text = data.name
            binding.iconImage.setImageResource(data.image)
            binding.clear.isVisible = isVisible
            this.position = position

            if (invertAmount(data.amount))
                binding.amountTV.text = data.amount.toInt().toString()
            else
            binding.amountTV.text = data.amount.toString()

            binding.amountTV.text = binding.amountTV.text.toString() + " ${data.currency}"

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
            binding.plus.setOnClickListener { changeAmount(true) }
            binding.minus.setOnClickListener { changeAmount(false) }
        }

        private fun changeAmount(plus: Boolean) {
            isPlus = plus
            val dialog = DialogChangeAmountItem(itemView.context, this)
            dialog.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        private fun invertAmount(max: Double): Boolean {
            return max == max.toInt().toDouble()
        }

        override fun amountSetChanged(amountItem: Double) {
            if (isPlus)
                listener.changeAmount(amountItem, position)
            else
                listener.changeAmount(-amountItem, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_activity_finance, parent, false)
        return ViewHolder(item, this)
    }

    override fun getItemCount(): Int {
        return mutableListFinanceItem.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mutableListFinanceItem[position], position, checkBoxVisible)
    }

    fun add(name: String, startCapital: Double, image: Int, color: Int, currency: String) {
        mutableListFinanceItem.add(InformationAboutItemFinance(name, startCapital, color, image, currency))
        notifyDataSetChanged()
    }

    fun getList() = mutableListFinanceItem

    fun checkBoxVisibility() {
        checkBoxVisible = !checkBoxVisible
        notifyDataSetChanged()
    }

    data class InformationAboutItemFinance(
        val name: String,
        var amount: Double,
        val color: Int,
        var image: Int,
        val currency: String
        )

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
}
interface ItemChanges {
    fun deleteItem(position: Int)
    fun changeAmount(money: Double, position: Int)

}