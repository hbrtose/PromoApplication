package com.hubose.promoapplication.mainview

import android.net.Uri
import com.hubose.promoapplication.common.ImageLoader
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MediaPagerAdapter(
    private val loader: ImageLoader,
    private var uris: List<String>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    init {
        if (uris.isNotEmpty()) {
            loader.preload(Uri.parse(uris[0]))
        }
    }

    override fun getCount(): Int {
        return uris.size
    }

    override fun getItem(position: Int): Fragment {
        if (position < uris.size - 1) {
            loader.preload(Uri.parse(uris[position + 1]))
        }
        return MediaFragment.newInstance(uris[position])
    }

    fun setUris(newUris: List<String>) {
        uris = newUris
        notifyDataSetChanged()
    }
}