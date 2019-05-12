package com.hubose.domain.usecase

import com.hubose.domain.NetworkRepository
import com.hubose.domain.common.ContinousTransformer
import com.hubose.domain.common.ContinousUseCase
import io.reactivex.Observable

class CheckInternetConnectivity(transformer: ContinousTransformer<Boolean>,
                                private val repository: NetworkRepository): ContinousUseCase<Any, Boolean>(transformer){

    override fun createObservable(data: Any?): Observable<Boolean> {
        return repository.checkInternetConnection()
    }
}