package com.hubose.promoapplication.common

import android.content.Context
import android.net.NetworkInfo
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.hubose.domain.NetworkRepository
import io.reactivex.Observable

class NetworkRepositoryImpl(private val context: Context): NetworkRepository{

    override fun checkInternetConnection(): Observable<Boolean> {
        return ReactiveNetwork.observeNetworkConnectivity(context).map{
            it.state() == NetworkInfo.State.CONNECTED
        }.distinctUntilChanged()
    }
}