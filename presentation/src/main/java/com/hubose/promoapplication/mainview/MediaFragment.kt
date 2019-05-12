package com.hubose.promoapplication.mainview

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.hubose.promoapplication.R
import com.hubose.promoapplication.common.ImageLoader
import kotlinx.android.synthetic.main.item_pager.*
import org.koin.android.ext.android.inject

class MediaFragment: Fragment(){

    private val loader: ImageLoader by inject()
    private lateinit var uri: Uri

    companion object {
        val VIDEO_EXTENSIONS = arrayOf("mp4", "avi", "mpg", "webm", "3gp")
        const val URI = "uri"

        fun newInstance(uri: String): MediaFragment {
            return MediaFragment().apply {
                arguments = bundleOf(URI to uri)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        uri = Uri.parse(arguments?.getString(URI))
        return inflater.inflate(R.layout.item_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loader.load(iv_pager, uri)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            if(isVideo(uri)) {
                vw_pager.visibility = View.VISIBLE
                vw_pager.setVideoURI(uri)
                vw_pager.setOnCompletionListener { vw_pager.start() }
                vw_pager.setOnPreparedListener {
                    iv_pager.animate().alpha(0f)
                    it.isLooping = true
                    it.start()
                }
            }
        }, (context?.resources?.getInteger(R.integer.crossfade_length_seconds) ?: 0) * 1000L)
    }

    override fun onPause() {
        super.onPause()
        if(isVideo(uri)) {
            vw_pager.stopPlayback()
            vw_pager.animate().alpha(0.0f)
        }
    }

    private fun isVideo(uri: Uri): Boolean {
        val type = if (uri.scheme?.equals(ContentResolver.SCHEME_CONTENT) == true) {
            val cr = context?.contentResolver
            cr?.getType(uri)
        } else {
            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
                .toString())
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                fileExtension.toLowerCase())
        }
        return VIDEO_EXTENSIONS.contains(type?.substring(type.indexOf("/")+1))
    }
}