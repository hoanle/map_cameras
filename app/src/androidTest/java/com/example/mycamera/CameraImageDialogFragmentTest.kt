package com.example.mycamera

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.mycamera.fragment.CameraImageDialogFragment
import com.example.mycamera.model.Camera
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class CameraImageDialogFragmentTest {

    @Before
    fun setUp() {
        val camera: Camera = mockk()

        val factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                if (className == CameraImageDialogFragment::class.java.name) {
                    return CameraImageDialogFragment(camera)
                }
                return super.instantiate(classLoader, className)
            }
        }
        launchFragmentInContainer<CameraImageDialogFragment>(null, factory = factory)
    }

    @Test
    fun launchFragmentAndVerifyUI() {
        Espresso.onView(ViewMatchers.withId(R.id.ivCamera)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }
}