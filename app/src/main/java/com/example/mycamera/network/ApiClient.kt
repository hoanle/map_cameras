package com.example.mycamera.network

import com.example.mycamera.model.Camera
import com.example.mycamera.network.deserializer.CameraDeserializer
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * The Retrofit client for network api call
 * @logging to define level of log in the app
 * @httpClientBuilder to attach additional setting, such as timeout at 30 seconds
 */
class ApiClient {

    private val baseURL = "https://api.data.gov.sg/"
    private val timeOut = 30L

    /**
     * Return the service
     */
    fun getService(): ApiInterface {
        return getRetrofit().create(ApiInterface::class.java)
    }

    /**
     * Create an instance of Retrofit with logging object and other settings
     * @logging level is now Level.BODY, should change to none when release
     */
    private fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClientBuilder =
            OkHttpClient.Builder().readTimeout(timeOut, TimeUnit.SECONDS)
                .connectTimeout(timeOut, TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(logging)
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(httpClientBuilder.build())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .registerTypeAdapter(
                            Camera::class.java,
                            CameraDeserializer()
                        ).create()
                )
            )
            .build()
    }
}
