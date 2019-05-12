package com.hubose.domain

import io.reactivex.Observable

interface NetworkRepository {
    fun checkInternetConnection(): Observable<Boolean>
}