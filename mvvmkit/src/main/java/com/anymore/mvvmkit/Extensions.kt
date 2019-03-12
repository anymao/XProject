package com.anymore.mvvmkit

import android.app.Application
import android.content.Context
import com.anymore.mvvmkit.di.component.RepositoryComponent
import com.anymore.mvvmkit.mvvm.lifecycle.KitApplication

/**
 * Created by liuyuanmao on 2019/3/12.
 */
fun Application.getRepositoryComponent():RepositoryComponent{
    if (this is KitApplication){
        return getRepositoryComponent()
    }
    throw RuntimeException("your application does not ....")
}

fun Context.getRepositoryComponent():RepositoryComponent{
    return this.applicationContext.getRepositoryComponent()
}