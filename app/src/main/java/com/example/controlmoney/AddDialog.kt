package com.example.controlmoney

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import com.example.controlmoney.DialogChangeCurrency.ChangeCurrencyDialog
import com.example.controlmoney.databinding.DialogAddBinding
import java.text.DecimalFormat

class AddDialog(context: Context) : Dialog(context), DialogCallBackStyleAndCurrency {

    private lateinit var binding: DialogAddBinding
    private lateinit var callBack: DialogCallBack
    private var icon: Int = R.drawable.home
    private var color: Int = Color.parseColor("#2A2A5F")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeStyleItem()
        changeCurrencyItem()
        createItem()
    }

    private fun changeCurrencyItem() {
        binding.currencyTV.setOnClickListener {
            val dialog = ChangeCurrencyDialog(context, null, this)
            dialog.show()
        }
    }

    private fun changeStyleItem() {
        binding.cardStyleView.setOnClickListener {
            val styleDialog = SelectStyleDialog(context, this)
            styleDialog.create()
            styleDialog.show()
        }
    }

    private fun createItem() {
        binding.button.setOnClickListener {
            if (binding.nameEditText.text.isNullOrEmpty())
                Toast.makeText(context, "Write name.", Toast.LENGTH_SHORT).show()
            else {
                val name = binding.nameEditText.text.toString()
                val startCapital = binding.startCapitalEditText.text.toString()
                val currency = binding.currencyTV.text.toString()
                callBack.returnDataAddItem(name, checkCapital(startCapital), icon, color, currency)
                dismiss()
            }
        }
    }

    private fun checkCapital(startCapital: String): Double {
        if (startCapital.isNullOrEmpty() || startCapital == ".")
            return 0.0
        return DecimalFormat("#.##").format(startCapital.toDouble()).toDouble()
    }

    fun setCallBack(callBack: DialogCallBack) {
        this.callBack = callBack
    }

    override fun setDataStyle(icon: Int, color: Int) {
        binding.cardStyleView.setCardBackgroundColor(color)
        binding.futureIcon.setImageResource(icon)
        this.icon = icon
        this.color = color
    }

    override fun setCurrencyName(currency: String) {
        binding.currencyTV.text = currency
    }
}
interface DialogCallBackStyleAndCurrency {
    fun setDataStyle(icon: Int, color: Int)
    fun setCurrencyName(currency: String)
}