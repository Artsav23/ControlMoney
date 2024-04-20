package com.example.controlmoney

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.controlmoney.databinding.ItemActivityFinanceBinding


class AdapterForResultsFragmentRecycleView: RecyclerView.Adapter<AdapterForResultsFragmentRecycleView.ViewHolder>(),DeleteItem {

    private var checkBoxVisible = false
    private val listColors = listOf<Int>(
        Color.parseColor("#2A2A5F"),
        Color.parseColor("#3D5AFE"),
        Color.parseColor("#536DFE"),
        Color.parseColor("#1E88E5"),
        Color.parseColor("#1976D2"),
        Color.parseColor("#1565C0"),
        Color.parseColor("#0D47A1"),
        Color.parseColor("#42A5F5"),
        Color.parseColor("#64B5F6"),
        Color.parseColor("#90CAF9"),
        Color.parseColor("#BBDEFB"),
    )

    private var mutableListFinanceItem = mutableListOf(
        InformationAboutFinance("Кредит", 230.0,  listColors[0], R.drawable.cafe),
        InformationAboutFinance("Коммуналка", 560.0,  listColors[1], R.drawable.home),
        InformationAboutFinance("Продукты", 136.38,  listColors[2], R.drawable.cinema),
        InformationAboutFinance("Подарок на 8 марта", 60.78,  listColors[3], R.drawable.delivery),
        InformationAboutFinance("Развлечение", 78.03,  listColors[4], R.drawable.cinema),
        InformationAboutFinance("Ресторан", 120.45,  listColors[5], R.drawable.cafe),
        InformationAboutFinance("Credit", 1.0,  listColors[6], R.drawable.home),
        InformationAboutFinance("Credit", 1.0,  listColors[7], R.drawable.home),
        InformationAboutFinance("Credit", 1.0,  listColors[8], R.drawable.home),
        InformationAboutFinance("Credit", 1.0,  listColors[9], R.drawable.cafe),
        InformationAboutFinance("Credit", 1.0,  listColors[10], R.drawable.home)
    )
    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {

        private var binding = ItemActivityFinanceBinding.bind(item)

        fun bind(data: InformationAboutFinance, position: Int, isVisible: Boolean, listener: DeleteItem) {
            binding.cardView.setCardBackgroundColor(data.color)
            if (invertAmount(data.amount))
                binding.amountTV.text = data.amount.toInt().toString()
            else
                binding.amountTV.text = data.amount.toString()
            binding.nameTV.text = data.name
            binding.iconImage.setImageResource(data.image)

            binding.clear.isVisible = isVisible
            binding.clear.setOnClickListener {
                val anim = AnimationUtils.loadAnimation(itemView.context, R.anim.delete_anim)
                anim.setAnimationListener(object : AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {}
                    override fun onAnimationRepeat(p0: Animation?) {}
                    override fun onAnimationEnd(p0: Animation?) {
                        listener.delete(position)
                    }})
                itemView.startAnimation(anim)
            }
        }

        private fun invertAmount(max: Double): Boolean {
            return max == max.toInt().toDouble()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_activity_finance, parent, false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return mutableListFinanceItem.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mutableListFinanceItem[position], position, checkBoxVisible, this)
    }

    fun add(name: String, startCapital: Double, image: Int) {
        val color = listColors.random()
        mutableListFinanceItem.add(InformationAboutFinance(name, startCapital, color, image))
        notifyDataSetChanged()
    }

    fun getList() = mutableListFinanceItem

    fun checkBoxVisibility() {
        checkBoxVisible = !checkBoxVisible
        notifyDataSetChanged()
    }

    data class InformationAboutFinance(
        val name: String,
        var amount: Double,
        val color: Int,
        var image: Int
        )

    override fun delete(position: Int) {
        mutableListFinanceItem.removeAt(position)
        notifyDataSetChanged()
    }
}
interface DeleteItem {
    fun delete(position: Int)
}