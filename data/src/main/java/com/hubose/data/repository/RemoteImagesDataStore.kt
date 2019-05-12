package com.hubose.data.repository

import com.hubose.data.api.Api
import com.hubose.domain.MediaDataStore
import io.reactivex.Single

class RemoteImagesDataStore(private val api: Api): MediaDataStore {

    override fun getMedia(): Single<List<String>> {
        return api.getImages().map {remote ->
            remote.map {
                it.urls.raw
            }
        }
    }

}