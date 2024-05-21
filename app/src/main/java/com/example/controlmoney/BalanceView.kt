package com.example.controlmoney
import android.view.View
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Canvas
import android.content.Context
import android.util.AttributeSet
import android.animation.ValueAnimator
import android.util.Log
import kotlin.math.log

class BalanceView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val paint = Paint().apply {
        color = Color.parseColor("#23238B")
        style = Paint.Style.STROKE
        strokeWidth = 60f
    }

    private var financeList = mutableListOf<FinanceParameters>(
    )

    private lateinit var animator: ValueAnimator
    private lateinit var animatorOfAmount: ValueAnimator
    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f
    private var angleForOneDegree = 360.0
    private var max = 0.0
    private var count = 0
    private var flagInit = false

    init {
        countMaxFinance()
        startAnimation()
    }

    fun count() = max
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!flagInit) {
            centerX = width / 2f
            centerY = height / 2f
            radius = if (centerX > centerY) centerY / 1.5f else centerX / 1.5f
            countMaxFinance()
            flagInit = true
        }
        drawCircle(canvas)
    }

    private fun countMaxFinance() {
        max = 0.0
            financeList.forEach {
            max += it.countMoney
        }
        angleForOneDegree = 360.0 / max
    }

    private fun startAnimation() {
        animator = ValueAnimator.ofFloat(0f, 1f)
        animator.apply {
            duration = 1200
            addUpdateListener {
                invalidate()
            }
            start()
        }
    }

    private fun drawCircle(canvas: Canvas) {
        paint.color = Color.WHITE
        var startAngle = 0.0
        val oval = RectF (
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )
        canvas.drawArc(oval, startAngle.toFloat(), 360f, false, paint)
        financeList.forEach {
            paint.color = it.color
            val sweepAngle = angleForOneDegree * it.countMoney * animator.animatedFraction
            count++
            canvas.drawArc(oval, startAngle.toFloat() - 1, sweepAngle.toFloat() - 2, false, paint)
            startAngle += sweepAngle
        }
    }

    fun addList(list: MutableList<DataItems.ItemFinanceParameters>) {
        financeList.clear()
        list.forEach {
            financeList.add(FinanceParameters(it.convertCurrency, it.color))
        }
        countMaxFinance()
        startAnimation()
        invalidate()
    }

    data class FinanceParameters(
        var countMoney: Double,
        val color: Int
    )
}
