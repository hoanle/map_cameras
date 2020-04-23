package com.example.mycamera.model

import com.google.gson.annotations.SerializedName

data class ImageMetaData(
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int,
    @SerializedName("md5")
    val md5: String
)