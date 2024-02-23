package com.example.controlmoney

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.controlmoney.databinding.ItemActivityFinanceBinding

class StatisticFragmentsAdapter: RecyclerView.Adapter<StatisticFragmentsAdapter.ViewHolder>() {

    val listColors = listOf<Int>(
        Color.parseColor("#23238B"),
        Color.parseColor("#009688"),
        Color.parseColor("#673AB7"),
        Color.parseColor("#FF9800"),
        Color.parseColor("#00BCD4"),
        Color.parseColor("#8AD632"),
        Color.parseColor("#8C9EFF"),
        Color.parseColor("#FF8A80"),
        Color.parseColor("#FFE57F"),
        Color.parseColor("#FF80AB")
    )
    private var mutableListFinanceItem = mutableListOf(InformationAboutFinance("Credit", 1000.0,  listColors[0]))
    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        private var binding = ItemActivityFinanceBinding.bind(item)

        fun bind(data: InformationAboutFinance) {
            binding.cardView.setBackgroundColor(data.color)
            binding.amountTV.text = data.amount.toString()
            binding.nameTV.text = data.name
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
        holder.bind(mutableListFinanceItem[position])
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