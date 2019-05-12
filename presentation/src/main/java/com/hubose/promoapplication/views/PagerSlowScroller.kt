package com.hubose.promoapplication.views

import android.content.Context
import android.view.animation.Interpolator
import android.widget.Scroller

class PagerSlowScroller(context: Context, interpolator: Interpolator,
                        private val time: Int): Scroller(context, interpolator) {

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        super.startScroll(startX, startY, dx, dy, time * 1000)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, time * 1000)
    }
}