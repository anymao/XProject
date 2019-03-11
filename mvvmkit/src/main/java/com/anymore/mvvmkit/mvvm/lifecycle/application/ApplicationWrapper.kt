package com.anymore.mvvmkit.mvvm.lifecycle.application

import android.app.Application
import com.anymore.mvvmkit.repository.RepositoryInjector

/**
 * Created by liuyuanmao on 2019/3/11.
 */

interface IApplicationLifecycle{
    fun attachBaseContext()
    fun onCreate()
    fun onTerminate()
}

class ApplicationWrapper(private val application: Application):IApplicationLifecycle {

    private val mRepositoryInjector by lazy { RepositoryInjector(application) }

    override fun attachBaseContext() {
        mRepositoryInjector.onCreate()
    }

    override fun onCreate() {

    }

    override fun onTerminate() {

    }



}