package com.example.mycamera.handler

import com.example.mycamera.model.Camera
import com.example.mycamera.model.CameraItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlin.collections.set

class MapHandler : OnMapReadyCallback, IMapHandler, GoogleMap.OnMarkerClickListener {

    private var gmap: GoogleMap? = null

    private val cameraList = HashMap<Int, Camera>()

    private val markerList = HashMap<String, Int>()

    private val actionSubject = PublishSubject.create<ClickOnMarker>()

    /**
     * When the map is ready, set some settings and the original location
     * @param googleMap: Instance of GoogleMap object
     */

    override fun onMapReady(googleMap: GoogleMap?) {
        gmap = googleMap
        gmap?.setMinZoomPreference(12.0f)

        //Singapore as original location
        val ny = LatLng(1.34355015, 103.8601984)

        gmap?.moveCamera(CameraUpdateFactory.newLatLng(ny))
        gmap?.setOnMarkerClickListener(this)
    }

    /**
     * Add a list of markers to the map, marker is constructed from Camera object
     * @param items: List of Camera items from api
     */
    override fun addMarkers(items: List<CameraItem>) {
        items.forEach {
            it.cameras.forEach { camera ->
                if (!cameraList.contains(camera.cameraId)) {
                    //Based on the data, the camera location are rarely changed. What changes is the image only.
                    //So only add marker if the camera is not in the current list
                    gmap?.addMarker(
                        MarkerOptions().position(
                            LatLng(
                                camera.location.latitude,
                                camera.location.longitude
                            )
                        )
                    )?.let { marker ->
                        markerList[marker.id] = camera.cameraId
                    }
                }
                //Always update new camera data to the camera hash map.
                cameraList[camera.cameraId] = camera
            }
        }
    }

    fun getCameraList(): HashMap<Int, Camera> {
        return cameraList
    }

    /**
     * When user clicks on a marker, the action subject should notify all listener
     * @param marker: The marker object which user clicks
     */
    override fun onMarkerClick(marker: Marker?): Boolean {
        marker?.let {
            val cameraId = markerList[it.id]
            val camera = cameraList[cameraId]
            actionSubject.onNext(ClickOnMarker(camera))
        }
        return true
    }

    /**
     * Return publish subject for listener to listen
     */
    override fun listen(): PublishSubject<ClickOnMarker> {
        return actionSubject
    }
}

/**
 * Interface for Map Handler
 */
interface IMapHandler {
    fun addMarkers(items: List<CameraItem>)
    fun listen(): PublishSubject<ClickOnMarker>
}