package com.example.mycamera.di

import com.example.mycamera.fragment.CameraImageDialogFragment
import com.example.mycamera.handler.MapHandler
import com.example.mycamera.model.Camera
import com.example.mycamera.network.ApiClient
import com.example.mycamera.repository.CameraRepository
import com.example.mycamera.repository.CameraRepositoryImpl
import com.example.mycamera.view_model.CameraActivityViewModel
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.SupportMapFragment
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModules = module {
    factory<CameraRepository> {
        CameraRepositoryImpl(apiInterface = get())
    }
}

val networkModule = module {
    single {
        ApiClient().getService()
    }
}
val viewModelModules = module {
    viewModel {
        CameraActivityViewModel(cameraRepository = get())
    }
}

val fragmentModules = module {
    factory { (camera: Camera) ->
        CameraImageDialogFragment(camera = camera)
    }

    factory { (options: GoogleMapOptions?) ->
        SupportMapFragment.newInstance(options)
    }
}
val handlerModules = module {
    factory {
        MapHandler()
    }
}

// Merge all modules to app modules list
val appModules = listOf(
    networkModule,
    repositoryModules,
    viewModelModules,
    fragmentModules,
    handlerModules
)
