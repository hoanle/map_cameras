package com.example.mycamera.network.deserializer

import com.example.mycamera.util.getInt
import com.example.mycamera.util.getString
import com.example.mycamera.model.Camera
import com.example.mycamera.model.ImageMetaData
import com.example.mycamera.model.Location
import com.google.gson.*
import java.lang.reflect.Type

/**
 * Class to define how Camera json object should be parsed to Camera object
 * To handle some null cases
 */
class CameraDeserializer : JsonDeserializer<Camera> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Camera {
        return (json as? JsonObject)?.let {
            Camera(
                it.getString("timestamp"),
                it.getString("image"),
                Gson().fromJson(it.get("location"), Location::class.java),
                it.getInt("camera_id"),
                Gson().fromJson(it.get("image_metadata"), ImageMetaData::class.java)
            )
        } ?: throw JsonParseException("Invalid Camera: $json")
    }
}
