package com.example.mycamera.model

import com.google.gson.annotations.SerializedName

data class AppInfo(
    @SerializedName("status")
    val status: String
)

