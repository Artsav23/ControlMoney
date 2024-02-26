package com.example.controlmoney

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.controlmoney.databinding.ItemActivityFinanceBinding

class AdapterForResultsFragmentRecycleView: RecyclerView.Adapter<AdapterForResultsFragmentRecycleView.ViewHolder>() {

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
        InformationAboutFinance("Кредит", 230.0,  listColors[0]),
        InformationAboutFinance("Коммуналка", 560.0,  listColors[1]),
        InformationAboutFinance("Продукты", 136.38,  listColors[2]),
        InformationAboutFinance("Подарок на 8 марта", 60.78,  listColors[3]),
        InformationAboutFinance("Развлечение", 78.03,  listColors[4]),
        InformationAboutFinance("Ресторан", 120.45,  listColors[5]),
        InformationAboutFinance("Credit", 1.0,  listColors[6]),
        InformationAboutFinance("Credit", 1.0,  listColors[7]),
        InformationAboutFinance("Credit", 1.0,  listColors[8]),
        InformationAboutFinance("Credit", 1.0,  listColors[9]),
        InformationAboutFinance("Credit", 1.0,  listColors[10])
    )
    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {

        private var binding = ItemActivityFinanceBinding.bind(item)

        fun bind(data: InformationAboutFinance, position: Int) {
            binding.cardView.setCardBackgroundColor(data.color)
            if (invertAmount(data.amount))
                binding.amountTV.text = data.amount.toInt().toString()
            else
                binding.amountTV.text = data.amount.toString()
            binding.nameTV.text = data.name
        }

        private fun invertAmount(max: Double): Boolean {
            return max == max.toInt().toDouble()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_activity_finance, parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return mutableListFinanceItem.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mutableListFinanceItem[position], position)
    }

    fun add(name: String, startCapital: Double) {
        val color = listColors.random()
        mutableListFinanceItem.add(InformationAboutFinance(name, startCapital, color))
        notifyDataSetChanged()
    }

    fun getList() = mutableListFinanceItem

    data class InformationAboutFinance(
        val name: String,
        var amount: Double,
        val color: Int
        )
}