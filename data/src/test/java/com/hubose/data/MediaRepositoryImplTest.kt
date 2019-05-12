package com.hubose.data

import com.hubose.data.api.Api
import com.hubose.data.entity.RemoteImage
import com.hubose.data.repository.DiskMediaDataStore
import com.hubose.data.repository.MediaRepositoryImpl
import com.hubose.data.repository.MemoryStorage
import com.hubose.data.repository.RemoteImagesDataStore
import com.hubose.domain.MediaRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MediaRepositoryImplTest{

    private lateinit var api: Api
    private lateinit var remoteImagesDataStore: RemoteImagesDataStore
    private lateinit var mediaRepository: MediaRepository
    private lateinit var diskMediaDataStore: DiskMediaDataStore
    private lateinit var memoryStorage: MemoryStorage

    @Before
    fun before(){
        api = Mockito.mock(Api::class.java)
        memoryStorage = Mockito.mock(MemoryStorage::class.java)
        remoteImagesDataStore = RemoteImagesDataStore(api)
        diskMediaDataStore = DiskMediaDataStore(memoryStorage)
        mediaRepository = MediaRepositoryImpl(diskMediaDataStore, remoteImagesDataStore)
    }

    @Test
    fun getMediaWithInternet() {
        val expected = listOf(
            RemoteImage("red", "1",
                listOf(RemoteImage.CurrentUserCollection("1", true, 1, "1", "title", "2", null)),
                "desc", 12, "id", false, 4, RemoteImage.Links("download", "location", "html", "self"), "1",
                RemoteImage.Urls("full", "raw", "regular", "small", "thumb"), null, 123)
        )
        Mockito.`when`(api.getImages()).thenReturn(Single.just(expected))
        mediaRepository.getMedia(true).test().assertValue {
            it[0] == "raw"
        }.assertComplete()
    }

    @Test
    fun getMediaNoInternet() {
        val expected = listOf("raw")
        Mockito.`when`(memoryStorage.getUris()).thenReturn(expected)
        mediaRepository.getMedia(false).test().assertValue {
            it[0] == "raw"
        }.assertComplete()
    }
}