package com.hubose.promoapplication.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.hubose.promoapplication.R
import kotlinx.android.synthetic.main.view_clock.view.*
import java.util.*

class AnalogClock @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        inflate(context, R.layout.view_clock, this)
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.PromoAnalogClock, defStyleAttr, defStyleRes)
        face.setColorFilter((typedArray.getColor(R.styleable.PromoAnalogClock_faceTint, Color.WHITE)))
        hour.setColorFilter(typedArray.getColor(R.styleable.PromoAnalogClock_hourTint, Color.WHITE))
        minute.setColorFilter(typedArray.getColor(R.styleable.PromoAnalogClock_minuteTint, Color.WHITE))
        second.setColorFilter(typedArray.getColor(R.styleable.PromoAnalogClock_secondTint, Color.WHITE))
        alpha = typedArray.getFloat(R.styleable.PromoAnalogClock_clockAlpha, 1f)
        clipToOutline = true
        refreshTime()
    }

    fun refreshTime() {
        val calendar = Calendar.getInstance()
        val hourMillis = calendar[Calendar.HOUR]
        val minuteMillis = calendar[Calendar.MINUTE]
        val secondMillis = calendar[Calendar.SECOND]

        hour.rotation = ((360f / (12)) * (hourMillis + (1f/60*minuteMillis)))
        minute.rotation = ((360f / (60)) * (minuteMillis + (1f/60*secondMillis)))
        second.rotation = ((360f / (60)) * (secondMillis))
    }
}