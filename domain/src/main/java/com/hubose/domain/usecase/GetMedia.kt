package com.hubose.domain.usecase

import com.hubose.domain.MediaRepository
import com.hubose.domain.common.Transformer
import com.hubose.domain.common.UseCase
import io.reactivex.Single

class GetMedia(transformer: Transformer<List<String>>,
               private val repository: MediaRepository): UseCase<Boolean, List<String>>(transformer){

    override fun createObservable(data: Boolean?): Single<List<String>> {
        return repository.getMedia(data ?: false)
    }

    fun get(hasInternet: Boolean): Single<List<String>> {
        return observable(hasInternet)
    }
}