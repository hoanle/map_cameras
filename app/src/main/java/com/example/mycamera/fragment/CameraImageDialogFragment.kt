package com.example.mycamera.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.mycamera.R
import com.example.mycamera.databinding.DialogFragmentCameraImageBinding
import com.example.mycamera.model.Camera

/**
 * The fragment to show the map of the camera list
 * It extends BaseFragment for data binding supports
 */
class CameraImageDialogFragment(private val camera: Camera) : DialogFragment(),
    FragmentBindingInterface<DialogFragmentCameraImageBinding> {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<DialogFragmentCameraImageBinding>(
            inflater,
            R.layout.dialog_fragment_camera_image,
            null,
            false
        )

        performBinding(binding)
        return binding.root
    }

    /**
     * Perform data binding
     * @param binding instance of DialogFragmentCameraImageBinding
     */
    override fun performBinding(binding: DialogFragmentCameraImageBinding) {
        binding.root.setOnClickListener { dismiss() }
        binding.lifecycleOwner = viewLifecycleOwner
        binding.camera = camera
        binding.executePendingBindings()
    }

    /**
     * To make sure dialog is fullscreen
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val dialog = Dialog(
            requireContext(),
            android.R.style.Theme_DeviceDefault_Dialog_NoActionBar
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return dialog
    }
}