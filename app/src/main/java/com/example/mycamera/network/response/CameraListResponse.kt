package com.example.mycamera.network.response

import com.example.mycamera.model.AppInfo
import com.example.mycamera.model.CameraItem
import com.google.gson.annotations.SerializedName

data class CameraListResponse(
    @SerializedName("app_info")
    val appInfo: AppInfo,
    @SerializedName("items")
    val items: List<CameraItem>
)