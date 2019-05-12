package com.hubose.data.api

import com.hubose.data.entity.RemoteImage
import io.reactivex.Single
import retrofit2.http.GET

interface Api{

    @GET("photos/?per_page=100")
    fun getImages(): Single<List<RemoteImage>>
}