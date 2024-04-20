package com.example.controlmoney

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.controlmoney.RecycleViewsForSelectStyleDialog.ChooseIconListener
import com.example.controlmoney.RecycleViewsForSelectStyleDialog.ColorsAdapter
import com.example.controlmoney.RecycleViewsForSelectStyleDialog.IconsAdapter
import com.example.controlmoney.databinding.DialogSelectStyleBinding

class SelectStyleDialog(context: Context): Dialog(context), ChooseIconListener {

    private lateinit var binding: DialogSelectStyleBinding
    private var choiceColor = Color.parseColor("#2A2A5F")
    private var choiceIcon = R.drawable.home
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogSelectStyleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val colorsAdapter = ColorsAdapter()/////////
        val iconsAdapter = IconsAdapter(this)
        binding.recyclerViewColors.adapter = colorsAdapter
        binding.recyclerViewColors.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewIcons.adapter = iconsAdapter
        binding.recyclerViewIcons.layoutManager = GridLayoutManager(context, 4)
    }

    override fun changeChoice(image: Int) {
        choiceIcon = image
    }
}