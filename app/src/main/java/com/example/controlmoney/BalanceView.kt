package com.example.controlmoney
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class BalanceView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val listColors = listOf<Int>(
        Color.parseColor("#23238B"),
        Color.parseColor("#009688"),
        Color.parseColor("#673AB7"),
        Color.parseColor("#FF9800"),
        Color.parseColor("#00BCD4"),
        Color.parseColor("#8AD632"),
        Color.parseColor("#8C9EFF"),
        Color.parseColor("#FF8A80"),
        Color.parseColor("#FFE57F"),
        Color.parseColor("#FF80AB")
    )

    private val paint = Paint().apply {
        color = listColors[0]
        style = Paint.Style.STROKE
        strokeWidth = 30f
    }

    private val paintText = Paint().apply {
        color = listColors[0]
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 68f
        strokeWidth = 30f
    }

    private var financeList = mutableListOf<FinanceParameters>(
    )

    private lateinit var animator: ValueAnimator
    private lateinit var animatorOfAmount: ValueAnimator
    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f
    private var angleForOneDegree = 360.0
    private var max = 0
    private var count = 0
    private var flagInit = false

    init {
        countMaxFinance()
        startAnimation()
    }

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
        canvas.drawText(animatorOfAmount.animatedValue.toString(), centerX, centerY, paintText)
    }

    private fun countMaxFinance() {
        max = 0
            financeList.forEach {
            max += it.countMoney
        }
        angleForOneDegree = 360.0 / max
    }

    private fun startAnimation() {
        animator = ValueAnimator.ofFloat(0f, 1f)
        animator.apply {
            duration = 2000
            addUpdateListener {
                invalidate()
            }
            start()
        }

        animatorOfAmount = ValueAnimator.ofInt(0, max)
        animatorOfAmount.apply {
            duration = 2000
            addUpdateListener {
                invalidate()
            }
            start()
        }
    }

    private fun drawCircle(canvas: Canvas) {
        var startAngle = 0.0
        val oval = RectF(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )
        financeList.forEach {
            paint.color = it.color
            val sweepAngle = angleForOneDegree * it.countMoney * animator.animatedFraction
            count++
            canvas.drawArc(oval, startAngle.toFloat() - 1, sweepAngle.toFloat() - 2, false, paint)
            startAngle += sweepAngle
        }
    }

    fun add(countMoney: Int) {
        val color = listColors[(listColors.indices).random()]
        financeList.add(FinanceParameters(countMoney, color))
        countMaxFinance()
        startAnimation()
        invalidate()
    }

    data class FinanceParameters(
        var countMoney: Int,
        var color: Int
    )
}
