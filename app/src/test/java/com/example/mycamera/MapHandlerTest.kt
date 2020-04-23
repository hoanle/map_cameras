package com.example.mycamera

import com.example.mycamera.handler.MapHandler
import com.example.mycamera.model.Camera
import com.example.mycamera.model.CameraItem
import com.example.mycamera.model.Location
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class MapHandlerTest {
    private val mapHandler: MapHandler = MapHandler()

    @Test
    fun testAddMarkers() {
        val list = arrayListOf<CameraItem>()
        val listCamera = arrayListOf<Camera>()
        (0..9).forEach {
            val camera: Camera = mockk()
            every {
                camera.cameraId
            } returns it
            every {
                camera.location
            } returns Location(1.0001, 1.0002)
            listCamera.add(camera)
        }
        val item: CameraItem = mockk()
        every {
            item.cameras
        } returns listCamera

        list.add(item)

        mapHandler.addMarkers(list)
        assert(mapHandler.getCameraList().size == listCamera.size)
    }
}