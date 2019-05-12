package com.hubose.domain.common

import io.reactivex.Single

abstract class UseCase<P, T>(private val transformer: Transformer<T>) {

    protected abstract fun createObservable(data: P? = null): Single<T>

    fun observable(withData: P? = null): Single<T> {
        return createObservable(withData).compose(transformer)
    }

}