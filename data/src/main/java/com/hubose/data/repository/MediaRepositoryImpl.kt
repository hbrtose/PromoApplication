package com.hubose.data.repository

import com.hubose.domain.MediaRepository
import io.reactivex.Single

class MediaRepositoryImpl(private val local: DiskMediaDataStore,
                          private val remote: RemoteImagesDataStore): MediaRepository {

    override fun getMedia(hasInternet: Boolean): Single<List<String>> {
        return if (hasInternet) {
            remote.getMedia()
        } else {
            local.getMedia()
        }
    }

}