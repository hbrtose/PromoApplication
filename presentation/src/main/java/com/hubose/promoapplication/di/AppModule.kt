package com.hubose.promoapplication.di

import androidx.preference.PreferenceManager
import com.hubose.data.repository.*
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
    single<MemoryStorage> { DiskMemoryStorage(androidContext()) }
    single<MediaRepository> { MediaRepositoryImpl(DiskMediaDataStore(get()), RemoteImagesDataStore(get())) }
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