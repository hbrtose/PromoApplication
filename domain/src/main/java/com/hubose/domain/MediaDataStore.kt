package com.hubose.domain

import io.reactivex.Single

interface MediaDataStore{
    fun getMedia(): Single<List<String>>
}