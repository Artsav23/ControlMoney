package com.example.controlmoney.RecycleViewsForSelectStyleDialog

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.controlmoney.ChooseListener
import com.example.controlmoney.DataStyleItems
import com.example.controlmoney.R
import com.example.controlmoney.databinding.ItemRecycleViewIconsBinding

class IconsAdapter(private val listener: ChooseListener): Adapter<IconsAdapter.ViewHolder>(), OnClickListenerItem {

    private val listIcons = DataStyleItems().icons

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemRecycleViewIconsBinding.bind(item)

        fun bind(itemParameters: ItemParameters, position: Int, listenerItem: OnClickListenerItem) {
            binding.icon.setImageResource(itemParameters.image)
            if (itemParameters.isSelected)
                binding.cardView.setCardBackgroundColor(Color.parseColor("#424B5A"))
            else
                binding.cardView.setCardBackgroundColor(Color.parseColor("#373E4A"))

            itemView.setOnClickListener {
                listenerItem.clickItem(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_view_icons, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listIcons.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listIcons[position], position, this)
    }

    override fun clickItem(position: Int) {
        var num = 0
        listIcons.forEach {
            it.isSelected = position == num
            if (it.isSelected)
                listener.changeImageChoice(it.image)
            num ++
        }
        notifyDataSetChanged()

    }

    data class ItemParameters(
        var isSelected: Boolean,
        val image: Int
    )
}