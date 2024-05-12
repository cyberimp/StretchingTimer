package com.kalinasoft.stretchtimer

import android.content.Context
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class CircleTimer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val a = min(width, height)
        setMeasuredDimension(a, a)
    }
}