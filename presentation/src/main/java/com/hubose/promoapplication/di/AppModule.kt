package com.hubose.promoapplication.di

import androidx.preference.PreferenceManager
import com.hubose.data.repository.DiskMediaDataStore
import com.hubose.data.repository.MediaRepositoryImpl
import com.hubose.data.repository.RemoteImagesDataStore
import com.hubose.domain.MediaRepository
import com.hubose.domain.NetworkRepository
import com.hubose.domain.usecase.CheckInternetConnectivity
import com.hubose.domain.usecase.GetMedia
import com.hubose.promoapplication.common.*
import com.hubose.promoapplication.mainview.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val viewModels = module {
    viewModel { MainViewModel(get(), get(), get()) }
}

val dataModule = module {
    single<MediaRepository> { MediaRepositoryImpl(DiskMediaDataStore(androidContext()), RemoteImagesDataStore(get())) }
    single<NetworkRepository> { NetworkRepositoryImpl(androidContext()) }
}

val useCases = module {
    factory { GetMedia(AsyncTransformer(), get()) }
    factory { CheckInternetConnectivity(ContinousAsyncTransformer(), get()) }
}

val utils = module {
    single<ImageLoader> { GlideImageLoader(androidContext()) }
    factory { PreferenceManager.getDefaultSharedPreferences(androidContext()).getInt("time", 6) }
}