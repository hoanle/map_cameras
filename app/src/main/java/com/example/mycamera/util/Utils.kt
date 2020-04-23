package com.example.mycamera.util

import androidx.lifecycle.LiveData
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import io.reactivex.rxjava3.observers.TestObserver
import java.text.SimpleDateFormat
import java.util.*

fun JsonObject.getString(key: String): String {
    return (get(key) as? JsonPrimitive)?.asString ?: ""
}

fun JsonObject.getInt(key: String): Int {
    return (get(key) as? JsonPrimitive)?.asInt ?: 0
}

fun Long.toTimeString(): String {
    val sdf = SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss")
    sdf.timeZone = TimeZone.getTimeZone("GMT+8")
    return sdf.format(Date(this))
}

