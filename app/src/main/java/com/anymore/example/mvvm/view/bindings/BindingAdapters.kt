package com.anymore.example.mvvm.view.bindings

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView


object BindingAdapters {

    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @BindingAdapter("imageBitmap")
    fun loadImage(imageView: ImageView, bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)
    }


}
