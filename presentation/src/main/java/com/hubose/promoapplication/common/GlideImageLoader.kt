package com.hubose.promoapplication.common

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader(private val context: Context): ImageLoader{

    override fun load(imageView: ImageView, uri: Uri) {
        Glide.with(context).load(uri).into(imageView)
    }

    override fun preload(uri: Uri) {
        Glide.with(context).load(uri).preload()
    }
}