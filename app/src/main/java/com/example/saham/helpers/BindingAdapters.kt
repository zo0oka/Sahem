package com.example.saham.helpers

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.saham.R
import com.example.saham.helpers.Constants.BASE_URL
import com.google.android.material.imageview.ShapeableImageView

object BindingAdapters {
    @BindingAdapter("setImage")
    @JvmStatic
    fun setImage(imageView: ImageView, imageUrl: String?) {
        imageUrl.let {
            Glide.with(imageView.context).load(BASE_URL + imageUrl)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(imageView)
        }
    }

    @BindingAdapter("setShapeableImage")
    @JvmStatic
    fun setShapeableImage(imageView: ShapeableImageView, imageUrl: String?) {
        imageUrl.let {
            if (imageUrl != null) {
                Log.e("Image Url", imageUrl)
            }
            Glide.with(imageView.context).load(BASE_URL + imageUrl).into(imageView)
        }
    }
}
