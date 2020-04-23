package com.example.mycamera

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mycamera.network.ApiInterface
import com.example.mycamera.network.response.CameraListResponse
import com.example.mycamera.repository.CameraRepositoryImpl
import com.example.mycamera.view_model.CameraActivityViewModel
import com.jraska.livedata.TestObserver
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Rule
import org.junit.Test

class CameraActivityViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    private val apiInterface: ApiInterface = mockk()

    private val repository = CameraRepositoryImpl(apiInterface)

    private val cameraActivityViewModel = spyk(CameraActivityViewModel(repository))

    @Test
    fun viewModelGetCameraListAndEmitRightData() {

        val response: CameraListResponse = mockk()

        every {
            apiInterface.getCameraList(any())
        } returns Observable.just(response)

        every {
            cameraActivityViewModel.getSchedulerIO()
        } returns Schedulers.io()

        every {
            cameraActivityViewModel.getMainScheduler()
        } returns Schedulers.io()

        cameraActivityViewModel.getCameraList()

        TestObserver.test(cameraActivityViewModel.getCameraListResponseLiveData())
            .assertValue {
                it == response
            }
    }
}