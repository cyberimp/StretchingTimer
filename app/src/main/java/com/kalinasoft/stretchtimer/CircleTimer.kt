package com.kalinasoft.stretchtimer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.min


class CircleTimer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var strokeWidth = 4f
    private var maxTime = 2.0f
        set(value) {
            field = value
            invalidate()
        }
    private var curTime = 1.0f
        set(value) {
            field = value.coerceIn(0.0f..maxTime)
            invalidate()
        }

    private val startAngle = -90
    private var color = Color.DKGRAY
    private var rectF: RectF = RectF()
    private var backgroundPaint: Paint
    private var foregroundPaint: Paint

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CircleTimer,
            0, 0
        )

        //Reading values from the XML layout
        try {
            strokeWidth = typedArray.getDimension(
                R.styleable.CircleTimer_progressBarThickness,
                strokeWidth
            )
            maxTime = typedArray.getInt(R.styleable.CircleTimer_maxTime, maxTime.toInt()).toFloat()
            curTime = typedArray.getInt(R.styleable.CircleTimer_curTime, curTime.toInt()).toFloat()
            color = typedArray.getInt(R.styleable.CircleTimer_progressbarColor, color)

        } finally {
            typedArray.recycle()
        }

        backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        backgroundPaint.color = adjustAlpha(color)
        backgroundPaint.style = Paint.Style.STROKE
        backgroundPaint.strokeWidth = strokeWidth

        foregroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        foregroundPaint.color = color
        foregroundPaint.style = Paint.Style.STROKE
        foregroundPaint.strokeWidth = strokeWidth
    }

    private fun adjustAlpha(color: Int): Int {
        val alpha = Math.round(Color.alpha(color) * 0.3f)
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val a = min(width, height)
        setMeasuredDimension(a, a)
        rectF.set(0 + strokeWidth / 2, 0 + strokeWidth / 2, a - strokeWidth / 2, a - strokeWidth / 2)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawOval(rectF, backgroundPaint)
        val angle: Float = 360 * curTime / maxTime

        canvas.drawArc(rectF, startAngle.toFloat(), angle, false, foregroundPaint)
    }
}