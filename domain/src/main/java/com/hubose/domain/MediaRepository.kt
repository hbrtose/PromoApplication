package com.hubose.domain

import io.reactivex.Single

interface MediaRepository{
    fun getMedia(hasInternet: Boolean): Single<List<String>>
}