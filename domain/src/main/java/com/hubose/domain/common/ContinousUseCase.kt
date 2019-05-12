package com.hubose.domain.common

import io.reactivex.Observable

abstract class ContinousUseCase<P, T>(private val transformer: ContinousTransformer<T>) {

    protected abstract fun createObservable(data: P? = null): Observable<T>

    fun observable(withData: P? = null): Observable<T> {
        return createObservable(withData).compose(transformer)
    }

}