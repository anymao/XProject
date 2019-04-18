package com.anymore.example.app.cache

import android.content.Context
import com.anymore.mvvmkit.isNetworkConnected
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by liuyuanmao on 2019/4/18.
 */
class OkHttpCacheInterceptor(private val mContext: Context) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (mContext.isNetworkConnected()){

        }
        return chain.proceed(request)
    }

}