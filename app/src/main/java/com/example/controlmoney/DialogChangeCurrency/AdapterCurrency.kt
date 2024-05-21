package com.example.controlmoney.DialogChangeCurrency

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.controlmoney.DataItems
import com.example.controlmoney.R
import com.example.controlmoney.RecycleViewsForSelectStyleDialog.OnClickListenerItem
import com.example.controlmoney.databinding.ItemCurrencyBinding

class AdapterCurrency: RecyclerView.Adapter<AdapterCurrency.ViewHolder>(), OnClickListenerItem {

    private var positionChooseCurrency = 1
    private val mutableListCurrency = DataItems().currencies

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemCurrencyBinding.bind(item)

        fun bind(data: DataItems.CurrencyParameters, listener: OnClickListenerItem, position: Int) {
            binding.currencyTextView.text = data.nameCurrency
            val color = if (data.chooseFlag) "#373E4A" else "#2F343E"
            binding.cardView.setCardBackgroundColor(Color.parseColor(color))
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

    override fun clickItem(position: Int) {
        mutableListCurrency[positionChooseCurrency].chooseFlag = false
        mutableListCurrency[position].chooseFlag = true
        positionChooseCurrency = position
        notifyDataSetChanged()
    }

    fun getChooseCurrency(): String = mutableListCurrency[positionChooseCurrency].nameCurrency
}