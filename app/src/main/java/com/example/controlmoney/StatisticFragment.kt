package com.example.controlmoney

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.controlmoney.databinding.FragmentStatisticBinding
import kotlin.concurrent.thread


class StatisticFragment : Fragment() {

    private lateinit var binding: FragmentStatisticBinding
    private val adapter = StatisticFragmentsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentStatisticBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        thread {
        Thread.sleep(3000)
        activity?.runOnUiThread {
            binding.balanceView.add(1000)
        }
        }

    }

    private fun initRecycleView() {
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = GridLayoutManager(context, 2)
    }
}