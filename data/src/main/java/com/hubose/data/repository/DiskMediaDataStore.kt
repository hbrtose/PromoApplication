package com.hubose.data.repository

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.loader.content.CursorLoader
import com.hubose.domain.MediaDataStore
import com.hubose.domain.MediaRepository
import io.reactivex.Single
import java.io.File


class DiskMediaDataStore(private val context: Context) : MediaDataStore {

    override fun getMedia(): Single<List<String>> {
        return Single.just(getUrisFromDir())
    }

    private fun getUrisFromDir(): List<String> {

        var list = listOf<String>()
        val projection = arrayOf(
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.MEDIA_TYPE
        )

        val selection = "${MediaStore.Files.FileColumns.MEDIA_TYPE} = " +
                "${MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE} OR " +
                "${MediaStore.Files.FileColumns.MEDIA_TYPE} = " +
                "${MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO}"

        val queryUri = MediaStore.Files.getContentUri("external")

        val cursorLoader = CursorLoader(
            context,
            queryUri,
            projection,
            selection,
            null, // Selection args (none).
            MediaStore.Files.FileColumns.DATE_ADDED + " DESC" // Sort order.
        )

        val cursor = cursorLoader.loadInBackground()

        cursor?.let {
            list = (1..cursor.count).map {
                cursor.moveToNext()
                Uri.fromFile(File(cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)))).toString()
            }.filter {
                !it.contains(".thumb")
            }
        }
        return list
    }
}