package com.example.controlmoney.DialogChangeCurrency

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.controlmoney.DialogCallBack
import com.example.controlmoney.DialogCallBackStyleAndCurrency
import com.example.controlmoney.databinding.DialogChangeCurrencyBinding

class ChangeCurrencyDialog(context: Context, private val callBackFromStyleFragment: DialogCallBack?, private val callBackFromAddDialog: DialogCallBackStyleAndCurrency? ): Dialog(context) {

    private lateinit var binding: DialogChangeCurrencyBinding
    private var adapter = AdapterCurrency()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogChangeCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRcView()
        binding.applyBtn.setOnClickListener {
            callBackFromStyleFragment?.returnCurrencyName(adapter.getChooseCurrency())
            callBackFromAddDialog?.setCurrencyName(adapter.getChooseCurrency())
            dismiss()
        }
    }

    private fun initRcView() {
        binding.recyclerViewCurrency.adapter = adapter
        binding.recyclerViewCurrency.layoutManager = GridLayoutManager(context, 4)
    }

}
