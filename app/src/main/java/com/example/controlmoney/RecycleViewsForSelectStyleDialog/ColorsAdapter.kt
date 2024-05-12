package com.example.controlmoney.RecycleViewsForSelectStyleDialog

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.controlmoney.ChooseListener
import com.example.controlmoney.DataStyleItems
import com.example.controlmoney.R

class ColorsAdapter (private val listener: ChooseListener): Adapter<ColorsAdapter.ViewHolder>(), OnClickListenerItem {

    private val listColors = DataStyleItems().colors

    class ViewHolder(item: View, private val listenerColor: OnClickListenerItem): RecyclerView.ViewHolder(item) {
        fun bind(colorParameters: ColorParameters, position: Int) {

            val colorView = itemView.findViewById<CardView>(R.id.colorView)
            val cardView = itemView.findViewById<CardView>(R.id.cardView)
            colorView.setCardBackgroundColor(colorParameters.color)
            if (colorParameters.chooseFlag)
                cardView.setCardBackgroundColor(Color.parseColor("#373E4A"))
            else
                cardView.setCardBackgroundColor(Color.parseColor("#2F343E"))

            itemView.setOnClickListener {
                listenerColor.clickItem(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle_view_colors, parent, false)
        return ViewHolder(view, this)
    }

    override fun getItemCount(): Int {
        return listColors.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listColors[position], position)
    }

    data class ColorParameters (
        val color: Int,
        var chooseFlag: Boolean
        )

    override fun clickItem(position: Int) {
        var num = 0
        listColors.forEach {
            it.chooseFlag = num == position
            if (it.chooseFlag)
                listener.changeColorChoice(it.color)
            num++
        }
        notifyDataSetChanged()
    }
}
