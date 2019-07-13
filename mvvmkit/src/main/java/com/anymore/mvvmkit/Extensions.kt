package com.anymore.mvvmkit

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.anymore.mvvmkit.di.component.RepositoryComponent
import com.anymore.mvvmkit.mvvm.lifecycle.KitApplication

/**
 * Created by liuyuanmao on 2019/3/12.
 */
fun Application.getRepositoryComponent():RepositoryComponent{
    if (this is KitApplication){
        return getRepositoryComponent()
    }
    throw RuntimeException("your application:<${this.javaClass.name}> should extends KitApplication")
}

fun Context.getRepositoryComponent():RepositoryComponent{
    return (applicationContext as Application).getRepositoryComponent()
}


/**
 * 判断网络是否连接
 */
fun Context.isNetworkConnected():Boolean{
    val networkService: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info:NetworkInfo? = networkService.activeNetworkInfo
    return info?.isAvailable?:false
}

fun Context.sp2px(spValue: Int): Int {
    val fontScale = resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}

fun Context.dp2px(dpValue: Int):Int{
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}