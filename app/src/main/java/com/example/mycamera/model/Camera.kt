package com.example.mycamera.model

import com.google.gson.annotations.SerializedName

class Camera(
    @SerializedName("timestamp")
    val timeStamp: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("camera_id")
    val cameraId: Int,
    @SerializedName("image_metadata")
    val imageMetaData: ImageMetaData
) {
    /**
     * For simpler comparision, override the equals class
     */
    override fun equals(other: Any?): Boolean {
        if (other is Camera) {
            return other.cameraId == cameraId
        }
        return false
    }
}