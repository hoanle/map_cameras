package com.example.mycamera.model

import com.google.gson.annotations.SerializedName

data class CameraItem(
    @SerializedName("timestamp")
    val timeStamp: String,

    @SerializedName("cameras")
    val cameras: List<Camera>
)