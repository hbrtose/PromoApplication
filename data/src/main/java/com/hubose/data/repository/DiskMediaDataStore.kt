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

class DiskMediaDataStore(private val memoryStorage: MemoryStorage) : MediaDataStore {

    override fun getMedia(): Single<List<String>> {
        return Single.just(memoryStorage.getUris())
    }
}