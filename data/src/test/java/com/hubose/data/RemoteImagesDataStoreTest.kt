package com.hubose.data

import com.hubose.data.api.Api
import com.hubose.data.entity.RemoteImage
import com.hubose.data.repository.RemoteImagesDataStore
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class RemoteImagesDataStoreTest {

    private lateinit var api: Api
    private lateinit var remoteImagesDataStore: RemoteImagesDataStore

    @Before
    fun before(){
        api = Mockito.mock(Api::class.java)
        remoteImagesDataStore = RemoteImagesDataStore(api)
    }

    @Test
    fun getMediaTest(){
        val expected = listOf(RemoteImage("red", "1",
            listOf(RemoteImage.CurrentUserCollection("1", true, 1, "1", "title", "2", null)),
            "desc", 12, "id", false, 4, RemoteImage.Links("download", "location", "html", "self"), "1",
            RemoteImage.Urls("full", "raw", "regular", "small", "thumb"), null, 123))
        `when`(api.getImages()).thenReturn(Single.just(expected))
        remoteImagesDataStore.getMedia().test().assertValue {
            it[0] == "raw"
        }.assertComplete()
    }
}