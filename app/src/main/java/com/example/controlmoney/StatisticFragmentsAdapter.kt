package com.example.controlmoney

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.controlmoney.databinding.ItemActivityFinanceBinding

class StatisticFragmentsAdapter: RecyclerView.Adapter<StatisticFragmentsAdapter.ViewHolder>() {

    private var mutableListFinanceItem = mutableListOf(InformationAboutFinance(1000.0, "Credit"))

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        private var binding = ItemActivityFinanceBinding.bind(item)

        fun bind(data: InformationAboutFinance) {
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

    data class InformationAboutFinance(
        var amount: Double,
        val name: String)
}