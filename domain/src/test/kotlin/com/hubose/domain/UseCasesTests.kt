package com.hubose.domain
import com.hubose.domain.common.ContinousTestTransformer
import com.hubose.domain.common.TestTransformer
import com.hubose.domain.usecase.CheckInternetConnectivity
import com.hubose.domain.usecase.GetMedia
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito

class UseCasesTests {

    @Test
    fun getMediaTest(){
        val strings = listOf("1", "2")
        val mediaRepository = Mockito.mock(MediaRepository::class.java)
        val getMedia = GetMedia(TestTransformer(), mediaRepository)

        Mockito.`when`(mediaRepository.getMedia(false)).thenReturn(Single.just(strings))

        getMedia.get(false).test().assertValue {
            it[0] == "1" && it[1] == "2"
        }
    }

    @Test
    fun checkInternetTest(){
        val res = true
        val networkRepository = Mockito.mock(NetworkRepository::class.java)
        val getInternetInfo = CheckInternetConnectivity(ContinousTestTransformer(), networkRepository)

        Mockito.`when`(networkRepository.checkInternetConnection()).thenReturn(Observable.just(res))

        getInternetInfo.observable().test().assertValue {
            it == res
        }
    }
}