package com.example.controlmoney

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.controlmoney.databinding.DialogChangeAmountItemBinding
import java.text.DecimalFormat

class DialogChangeAmountItem(context: Context, private val changeAmount: ChangeAmount) : Dialog(context) {
    private lateinit var binding: DialogChangeAmountItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogChangeAmountItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply.setOnClickListener {
            if (binding.editTextNumberDecimal.text.toString().isNullOrEmpty())
                Toast.makeText(context, "Please, write amount.", Toast.LENGTH_SHORT).show()
            else
                returnAmount()
        }
    }

    private fun returnAmount() {
        val amountItem = checkCapital(binding.editTextNumberDecimal.text.toString())
        changeAmount.amountSetChanged(amountItem)
        dismiss()
    }
    private fun checkCapital(startCapital: String): Double {
        return DecimalFormat("#.##").format(startCapital.toDouble()).toDouble()
    }
}