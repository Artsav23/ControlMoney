package com.example.controlmoney

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.controlmoney.RecycleViewsForSelectStyleDialog.ColorsAdapter
import com.example.controlmoney.RecycleViewsForSelectStyleDialog.IconsAdapter
import com.example.controlmoney.databinding.DialogSelectStyleBinding

class SelectStyleDialog(context: Context): Dialog(context) {

    private lateinit var binding: DialogSelectStyleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogSelectStyleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val colorsAdapter = ColorsAdapter()
        binding.recyclerViewColors.adapter = colorsAdapter
        binding.recyclerViewColors.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        val iconsAdapter = IconsAdapter()
        binding.recyclerViewIcons.adapter = iconsAdapter
        binding.recyclerViewIcons.layoutManager = GridLayoutManager(context, 4)
    }
}