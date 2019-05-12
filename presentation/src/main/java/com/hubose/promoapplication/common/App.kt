package com.hubose.promoapplication.common

import android.app.Application
import com.hubose.promoapplication.di.*
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(viewModels, dataModule, useCases, utils, networkModule))
    }
}