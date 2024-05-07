package com.example.controlmoney

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.controlmoney.databinding.FragmentStatisticBinding
import com.example.controlmoney.DialogChangeCurrency.ChangeCurrencyDialog


class StatisticFragment : Fragment(), DialogCallBack, ListenerChangeCountMoney {

    private lateinit var binding: FragmentStatisticBinding
    private val adapter = AdapterForResultsFragmentRecycleView(this)
    private var currency = "RUB"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentStatisticBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        binding.currencyTV.setOnClickListener { showDialogChangeCurrency()}
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.add -> startAddDialog()
                R.id.delete -> checkBoxVisibility()
            }
            true
        }

    }

    private fun showDialogChangeCurrency() {
        val dialog = context?.let { ChangeCurrencyDialog(it, this, null) }
        dialog?.show()
    }

    private fun checkBoxVisibility() {
        adapter.checkBoxVisibility()
    }

    private fun startAddDialog() {
        val  dialog = context?.let { AddDialog(it) }
        dialog?.create()
        dialog?.setCallBack(this)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.show()


    }
    private fun initRecycleView() {
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = GridLayoutManager(context, 2)
        startAnimationBalanceView()
    }

    override fun returnDataAddItem(name: String, startCapital: Double, image: Int, color: Int, currency: String) {
        adapter.add(name, startCapital, image, color, currency)
        startAnimationBalanceView()
    }

    override fun returnCurrencyName(currency: String) {
        binding.countMoney.text = "Score: " + binding.balanceView.count().toString()
        binding.currencyTV.text = currency
        this.currency = currency
    }

    private fun startAnimationBalanceView() {
        binding.balanceView.addList(adapter.getList())
        val count = binding.balanceView.count()
        val animator = if (invertMaxNum(count))  ValueAnimator.ofInt(0, count.toInt())
        else ValueAnimator.ofFloat(0f, count.toFloat())
        animator.duration = 1500
        animator.addUpdateListener {
            binding.countMoney.text = "Score: " + it.animatedValue.toString()
            binding.currencyTV.text = currency
        }
        animator.start()
    }
    private fun invertMaxNum(max: Double): Boolean {
        return max == max.toInt().toDouble()
    }

    override fun dataSetChanged() {
        startAnimationBalanceView()
    }
}
interface DialogCallBack {
    fun returnDataAddItem(name: String, startCapital: Double, image: Int, color: Int, currency: String)
    fun returnCurrencyName(currency: String)
}