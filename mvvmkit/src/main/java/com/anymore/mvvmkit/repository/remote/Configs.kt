package com.anymore.mvvmkit.repository.remote

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by liuyuanmao on 2019/3/7.
 */
interface OkHttpConfig{
    fun applyConfig(context: Context,builder: OkHttpClient.Builder)
}

interface GsonConfig{
    fun applyConfig(context: Context,builder: GsonBuilder)
}

interface RetrofitConfig{
    fun applyConfig(context: Context,builder: Retrofit.Builder)
}