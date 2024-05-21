package com.example.controlmoney

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.controlmoney.RecycleViewsForSelectStyleDialog.ColorsAdapter
import com.example.controlmoney.RecycleViewsForSelectStyleDialog.IconsAdapter
import com.example.controlmoney.databinding.DialogSelectStyleBinding

class SelectStyleDialog(context: Context, private val callBack: DialogCallBackStyleAndCurrency): Dialog(context), ChooseListener {

    private lateinit var binding: DialogSelectStyleBinding
    private var choiceColor = Color.parseColor("#BBDEFB")
    private var choiceIcon = R.drawable.cafe
    private val colorsAdapter = ColorsAdapter(this)
    private val iconsAdapter = IconsAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogSelectStyleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewColors.adapter = colorsAdapter
        binding.recyclerViewColors.layoutManager = GridLayoutManager(context, 5)
        binding.recyclerViewIcons.adapter = iconsAdapter
        binding.recyclerViewIcons.layoutManager = GridLayoutManager(context, 4)

        binding.applyBtn.setOnClickListener {
            callBack.setDataStyle(choiceIcon, choiceColor)
            dismiss()
        }
    }

    override fun changeImageChoice(image: Int) {
        choiceIcon = image
    }

    override fun changeColorChoice(color: Int) {
        choiceColor = color
        iconsAdapter.changeColor(choiceColor)
    }
}
interface ChooseListener {
    fun changeImageChoice(image: Int)
    fun changeColorChoice(color: Int)
}
