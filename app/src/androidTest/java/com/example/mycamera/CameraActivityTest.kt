package com.example.mycamera

import androidx.test.rule.ActivityTestRule
import com.example.mycamera.activity.CameraActivity
import com.google.android.gms.maps.SupportMapFragment
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class CameraActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(CameraActivity::class.java)

    @Test
    fun makeSureMainFragmentIsVisibleAndGetData() {
        val activity: CameraActivity = activityRule.activity

        val fragment = activity.supportFragmentManager.findFragmentById(R.id.map)
        Assert.assertEquals(true, fragment != null)
        Assert.assertEquals(true, fragment is SupportMapFragment)

        Thread.sleep(2000)
        assert(activity.mapHandler.getCameraList().size != null)
    }
}