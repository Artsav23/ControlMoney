package com.example.controlmoney.RecycleViewsForSelectStyleDialog

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.controlmoney.R

class ColorsAdapter: Adapter<ColorsAdapter.ViewHolder>(), OnClickListenerItem {

    private val listColors = listOf(
        ColorParameters(Color.parseColor("#2A2A5F"), true),
        ColorParameters(Color.parseColor("#3D5AFE"), false),
        ColorParameters(Color.parseColor("#536DFE"), false),
        ColorParameters(Color.parseColor("#1E88E5"), false),
        ColorParameters(Color.parseColor("#1976D2"), false),
        ColorParameters(Color.parseColor("#1565C0"), false),
        ColorParameters(Color.parseColor("#0D47A1"), false),
        ColorParameters(Color.parseColor("#42A5F5"), false),
        ColorParameters(Color.parseColor("#64B5F6"), false),
        ColorParameters(Color.parseColor("#90CAF9"), false),
        ColorParameters(Color.parseColor("#90CAF9"), false),
        ColorParameters(Color.parseColor("#BBDEFB"), false)
    )

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

    fun countColors() = listColors.size

    override fun clickItem(position: Int) {
        var num = 0
        listColors.forEach {
            it.chooseFlag = num == position
            num++
        }
        notifyDataSetChanged()
    }

}