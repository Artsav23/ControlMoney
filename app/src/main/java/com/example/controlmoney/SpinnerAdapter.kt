package com.example.controlmoney

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources


class SpinnerAdapter(context: Context, private val images: List<Int>): ArrayAdapter<Int>(context, R.layout.item_spinner, images) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_spinner, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.imageView2)
        imageView.setImageDrawable(AppCompatResources.getDrawable(context, images[position]))

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }

}