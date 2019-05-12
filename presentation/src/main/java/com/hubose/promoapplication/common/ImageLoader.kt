package com.hubose.promoapplication.common

import android.content.Context
import android.net.Uri
import android.widget.ImageView

interface ImageLoader{
    fun load(imageView: ImageView, uri: Uri)
    fun preload(uri: Uri)
}