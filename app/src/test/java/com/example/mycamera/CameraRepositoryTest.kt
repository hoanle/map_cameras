package com.example.mycamera

import com.example.mycamera.network.ApiInterface
import com.example.mycamera.network.response.CameraListResponse
import com.example.mycamera.repository.CameraRepositoryImpl
import com.example.mycamera.util.toTimeString
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class CameraRepositoryTest {

    private val apiInterface: ApiInterface = mockk()

    private val repository = CameraRepositoryImpl(apiInterface)

    @Test
    fun repositoryEmitsRightData() {
        val time = System.currentTimeMillis().toTimeString()
        val response: CameraListResponse = mockk()
        every {
            apiInterface.getCameraList(time)
        } returns Observable.just(response)

        val testObserver = repository.getCameraList(time).test()
        testObserver.assertValue(response)
        testObserver.dispose()
    }
}