package com.example.mycamera.network

import com.example.mycamera.network.response.CameraListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api Interface
 */
interface ApiInterface {

    @GET("v1/transport/traffic-images")
    fun getCameraList(
        @Query("date_time") dateTime: String
    ): Observable<CameraListResponse>
}