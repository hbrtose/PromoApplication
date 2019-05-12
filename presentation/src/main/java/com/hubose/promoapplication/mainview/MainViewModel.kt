package com.hubose.promoapplication.mainview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.hubose.domain.usecase.CheckInternetConnectivity
import com.hubose.domain.usecase.GetMedia
import com.hubose.promoapplication.common.BaseViewModel
import com.hubose.promoapplication.common.SingleLiveEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainViewModel(getMedia: GetMedia, checkInternetConnectivity: CheckInternetConnectivity, private val interval: Int): BaseViewModel(){

    private var items: MutableLiveData<List<String>> = MutableLiveData()
    var ticker = SingleLiveEvent<Int>()

    init {
        ticker.value = 0
        addDisposable(checkInternetConnectivity.observable()
            .flatMapSingle {
                getMedia.get(it)
            }.subscribe({
                items.value = it
            }, {
                error.value = it
            }))
        startTicking()
    }

    fun getItems(): LiveData<List<String>> {
        return items
    }

    private fun startTicking() {
        addDisposable(
            Observable.interval(interval.toLong(), TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { ticker.value = ticker.value?.plus(1) })
    }
}