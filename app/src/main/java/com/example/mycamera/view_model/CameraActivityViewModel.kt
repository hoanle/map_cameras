package com.example.mycamera.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mycamera.network.response.CameraListResponse
import com.example.mycamera.repository.CameraRepository
import com.example.mycamera.util.toTimeString
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

open class CameraActivityViewModel(private val cameraRepository: CameraRepository) :
    BaseViewModel(),
    ICameraActivityViewModel {

    private val cameraListResponseLiveData: MutableLiveData<CameraListResponse> =
        MutableLiveData<CameraListResponse>()

    /**
     * Start pulling the camera list from server every 1 minutes
     * @time should be in define format
     */
    override fun getCameraList() {
        val call = Single.just(1).repeatWhen { completed -> completed.delay(1, TimeUnit.MINUTES) }
            .subscribe({
                val time = System.currentTimeMillis().toTimeString()
                cameraRepository.getCameraList(time)
                    .observeOn(getMainScheduler())
                    .subscribeOn(getSchedulerIO())
                    .subscribe({
                        cameraListResponseLiveData.value = it
                    }, {
                        throwableLiveData.value = it
                    })
            }, {
                it.printStackTrace()
            })
        compositeDisposable.add(call)
    }

    /**
     * Return the live data of camera list response
     */
    fun getCameraListResponseLiveData(): LiveData<CameraListResponse> {
        return cameraListResponseLiveData
    }
}


interface ICameraActivityViewModel {
    fun getCameraList()
}

