package com.example.controlmoney

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.controlmoney.databinding.DialogChangeAmountItemBinding

class DialogChangeAmountItem(context: Context) : Dialog(context) {
    private lateinit var binding: DialogChangeAmountItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogChangeAmountItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}