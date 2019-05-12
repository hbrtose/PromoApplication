package com.hubose.domain.common

import io.reactivex.ObservableTransformer

abstract class ContinousTransformer<T> : ObservableTransformer<T, T>