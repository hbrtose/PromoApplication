package com.hubose.domain.common

import io.reactivex.Observable
import io.reactivex.ObservableSource

class ContinousTestTransformer<T>: ContinousTransformer<T>() {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
    }
}