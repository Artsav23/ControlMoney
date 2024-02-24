package com.example.controlmoney

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.controlmoney.databinding.DialogAddBinding
import java.text.DecimalFormat

class AddDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogAddBinding
    private lateinit var callBack: DialogCallBack
    private val images = listOf(
        R.drawable.cafe,
        R.drawable.car,
        R.drawable.cinema,
        R.drawable.circus,
        R.drawable.delivery,
        R.drawable.gym,
        R.drawable.home,
        R.drawable.museum
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.spinner.adapter = SpinnerAdapter(context, images)
        binding.spinner.dropDownWidth = 150



        binding.button.setOnClickListener {
            if (binding.nameEditText.text.isNullOrEmpty())
                Toast.makeText(context, "Write name.", Toast.LENGTH_SHORT).show()
            else {
                val name = binding.nameEditText.text.toString()
                val startCapital = binding.startCapitalEditText.text.toString()
                callBack.returnData(name, checkCapital(startCapital))
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

    interface DialogCallBack {
        fun returnData(name: String, startCapital: Double)
    }
}