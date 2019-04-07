package com.anymore.example.app.glide

import android.content.Context
import android.widget.ImageView
import com.youth.banner.loader.ImageLoader

class GlideImageLoader:ImageLoader(){
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        if (context != null && imageView != null){
            GlideApp.with(context)
                .load(path)
                .into(imageView)
        }
    }
}