package com.anymore.example.mvvm.view.bindings

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import android.view.View
import android.widget.ImageView
import com.anymore.example.app.glide.GlideApp


object BindingAdapters {

    @BindingAdapter("visibleGone")
    @JvmStatic fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @BindingAdapter("imageBitmap")
    @JvmStatic fun loadImage(imageView: ImageView, bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)
    }

    @BindingAdapter("imageRes")
    @JvmStatic fun loadImage(imageView: ImageView, @DrawableRes drawableRes: Int) {
        GlideApp.with(imageView)
            .load(drawableRes)
            .into(imageView)
    }


}
