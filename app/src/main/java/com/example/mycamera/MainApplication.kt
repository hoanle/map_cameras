package com.example.mycamera

import android.app.Application
import com.example.mycamera.di.appModules
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpDI()
    }

    /**
     * Start the entire app's  modules
     */
    private fun setUpDI() {
        startKoin {
            modules(appModules)
        }
    }
}