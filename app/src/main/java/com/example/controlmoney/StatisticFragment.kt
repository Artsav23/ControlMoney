package com.example.controlmoney

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.controlmoney.databinding.FragmentStatisticBinding


class StatisticFragment : Fragment(), AddDialog.DialogCallBack {

    private lateinit var binding: FragmentStatisticBinding
    private val adapter = StatisticFragmentsAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentStatisticBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        binding.toolbar.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.add -> startAddDialog()
        }
            true
        }
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
    }

    override fun returnData(name: String, startCapital: Double) {
        adapter.add(name, startCapital)
        binding.balanceView.addList(adapter.getList())
    }

}