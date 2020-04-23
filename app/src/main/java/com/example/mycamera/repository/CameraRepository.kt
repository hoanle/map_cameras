package com.example.mycamera.repository

import com.example.mycamera.network.ApiInterface
import com.example.mycamera.network.response.CameraListResponse
import io.reactivex.rxjava3.core.Observable

/**
 * The interface to define actions: get Reviews from remote and store in locals
 */
interface CameraRepository {
    fun getCameraList(dateTime: String): Observable<CameraListResponse>
}

class CameraRepositoryImpl(private val apiInterface: ApiInterface) :
    CameraRepository {

    /**
     * Perform network call to get the list of review
     * @param dateTime: timestamp when to get the camera images
     */
    override fun getCameraList(dateTime: String): Observable<CameraListResponse> {
        System.out.println("This is for testing 2")
        return apiInterface.getCameraList(dateTime)
    }
}