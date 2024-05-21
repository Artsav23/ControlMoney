package com.example.controlmoney.RecycleViewsForSelectStyleDialog

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.controlmoney.ChooseListener
import com.example.controlmoney.DataItems
import com.example.controlmoney.R

class ColorsAdapter (private val listener: ChooseListener): Adapter<ColorsAdapter.ViewHolder>(), OnClickListenerItem {

    private val listColors = DataItems().colorsParameters
    private var checkItem = 0

    class ViewHolder(item: View, private val listenerColor: OnClickListenerItem): RecyclerView.ViewHolder(item) {
        fun bind(data: DataItems.ColorParameters, position: Int) {

            val colorView = itemView.findViewById<CardView>(R.id.colorView)
            val cardView = itemView.findViewById<CardView>(R.id.cardView)
            colorView.setCardBackgroundColor(data.color)
            val color = if (data.chooseFlag) "#373E4A" else "#2F343E"
            cardView.setCardBackgroundColor(Color.parseColor(color))

            itemView.setOnClickListener {
                listenerColor.clickItem(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_view_colors, parent, false)
        return ViewHolder(view, this)
    }

    override fun getItemCount() = listColors.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listColors[position], position)
    }

    override fun clickItem(position: Int) {
        listColors[checkItem].chooseFlag = false
        listColors[position].chooseFlag = true
        listener.changeColorChoice(listColors[position].color)
        checkItem = position
        notifyDataSetChanged()
    }
}
