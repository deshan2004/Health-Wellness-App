package edu.cinec.healthwellness

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class BmiGaugeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val arcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 40f
        strokeCap = Paint.Cap.BUTT
    }

    private val needlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLACK
    }

    private var bmiValue: Float = 0f
    private val minBmi = 10f
    private val maxBmi = 40f

    fun setBmi(value: Float) {
        bmiValue = value.coerceIn(minBmi, maxBmi)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val radius = (width.coerceAtMost(height * 2) / 2) - 60f
        val centerX = width / 2
        val centerY = height - 40f

        val rectF = RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

        // Draw background segments
        // Underweight (10 - 18.5)
        arcPaint.color = Color.parseColor("#BFDBFE")
        canvas.drawArc(rectF, 180f, 180f * (18.5f - 10f) / (40f - 10f), false, arcPaint)

        // Normal (18.5 - 25)
        arcPaint.color = Color.parseColor("#10B981")
        canvas.drawArc(rectF, 180f + 180f * (18.5f - 10f) / (40f - 10f), 180f * (25f - 18.5f) / (40f - 10f), false, arcPaint)

        // Overweight (25 - 30)
        arcPaint.color = Color.parseColor("#FDE047")
        canvas.drawArc(rectF, 180f + 180f * (25f - 10f) / (40f - 10f), 180f * (30f - 25f) / (40f - 10f), false, arcPaint)

        // Obese (30 - 40)
        arcPaint.color = Color.parseColor("#FECACA")
        canvas.drawArc(rectF, 180f + 180f * (30f - 10f) / (40f - 10f), 180f * (40f - 30f) / (40f - 10f), false, arcPaint)

        // Draw Needle
        val angle = 180f + (bmiValue - minBmi) / (maxBmi - minBmi) * 180f
        val needleLength = radius - 20f
        val stopX = centerX + needleLength * cos(Math.toRadians(angle.toDouble())).toFloat()
        val stopY = centerY + needleLength * sin(Math.toRadians(angle.toDouble())).toFloat()

        needlePaint.strokeWidth = 8f
        canvas.drawLine(centerX, centerY, stopX, stopY, needlePaint)
        canvas.drawCircle(centerX, centerY, 15f, needlePaint)
    }
}