package com.example.controlmoney.RecycleViewsForSelectStyleDialog

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.controlmoney.ChooseListener
import com.example.controlmoney.DataItems
import com.example.controlmoney.R
import com.example.controlmoney.databinding.ItemRecycleViewIconsBinding

class IconsAdapter(private val listener: ChooseListener): Adapter<IconsAdapter.ViewHolder>(), OnClickListenerItem {

    private val listIcons = DataItems().icons
    private var choiceColor = Color.parseColor("#BBDEFB")
    private var checkItem = 0

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = ItemRecycleViewIconsBinding.bind(item)

        fun bind(data: DataItems.ItemParameters, position: Int, listenerItem: OnClickListenerItem, choiceColor: Int) {
            binding.icon.setImageResource(data.image)
            binding.icon.setColorFilter(choiceColor)
            val color = if (data.isSelected) "#424B5A" else "#373E4A"
            binding.cardView.setCardBackgroundColor(Color.parseColor(color))

            itemView.setOnClickListener {
                listenerItem.clickItem(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_view_icons, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount() = listIcons.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listIcons[position], position, this, choiceColor)
    }

    override fun clickItem(position: Int) {
        listIcons[checkItem].isSelected = false
        listIcons[position].isSelected = true
        listener.changeImageChoice(listIcons[position].image)
        checkItem = position
        notifyDataSetChanged()

    }
    fun changeColor(choiceColor: Int) {
        this.choiceColor = choiceColor
        notifyDataSetChanged()
    }
}