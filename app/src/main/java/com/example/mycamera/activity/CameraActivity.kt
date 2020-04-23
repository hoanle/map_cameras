package com.example.mycamera.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mycamera.R
import com.example.mycamera.fragment.CameraImageDialogFragment
import com.example.mycamera.handler.MapHandler
import com.example.mycamera.view_model.CameraActivityViewModel
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.SupportMapFragment
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * Main class to launch. It basically to hold the main fragment which was
 * injected into at the start
 */
class CameraActivity : AppCompatActivity() {

    // Instance of MapFragment
    private val mapFragment: SupportMapFragment by inject { parametersOf(GoogleMapOptions()) }

    // Instance of MapHandler
    val mapHandler: MapHandler by inject()

    // Instance of this activity view model
    private val cameraActivityViewModel: CameraActivityViewModel by inject()

    // The composite disposable to handle all the diposong
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.map, mapFragment).commit()
        mapFragment.getMapAsync(mapHandler)

        cameraActivityViewModel.getCameraListResponseLiveData().observe(this, Observer {
            mapHandler.addMarkers(it.items)
        })
    }

    /**
     * This should be either right after onCreate or in onResume depending on the optimisation level of the code
     */
    private fun afterCreationAction() {
        compositeDisposable.add(mapHandler.listen().subscribe({
            val imageDialog: CameraImageDialogFragment =
                get(parameters = { parametersOf(it.camera) })
            imageDialog.show(supportFragmentManager, "")
        }, {
            it.printStackTrace()
        }))
        cameraActivityViewModel.getCameraList()
    }

    /**
     * On resume of the app, perform register listener and get the camera list
     * @Optimise: To check the time of previous resume
     */
    override fun onResume() {
        super.onResume()
        afterCreationAction()
    }

    /**
     * Dispose all the disposable to avoid memory leak
     */
    override fun onStop() {
        cameraActivityViewModel.dispose()
        compositeDisposable.dispose()
        super.onStop()
    }
}
