package com.example.mycamera.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class DataBindingUtils {

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImageUrl(iv: ImageView, url: String?) {
            Glide.with(iv.context)
                .load(url)
                .into(
                    iv
                )

        }
    }
}