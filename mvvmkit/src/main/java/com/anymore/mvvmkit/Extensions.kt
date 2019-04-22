package com.anymore.mvvmkit

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
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

fun Context.isNetworkConnected():Boolean{
    val networkService: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = networkService.activeNetworkInfo
    return info.isAvailable
}