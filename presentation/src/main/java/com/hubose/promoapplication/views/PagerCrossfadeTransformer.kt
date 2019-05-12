package com.hubose.promoapplication.views

import android.view.View
import androidx.viewpager.widget.ViewPager

class PagerCrossfadeTransformer(): ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        page.translationX = page.width * -position
        if(position <= -1f|| position >= 1f) {
            page.alpha = 0f
        } else if( position == 0f ) {
            page.alpha = 1f
        } else {
            page.alpha = 1f - Math.abs(position)
        }
    }
}