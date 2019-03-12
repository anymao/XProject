package com.anymore.mvvmkit.mvvm.lifecycle.application

import android.app.Application
import com.anymore.mvvmkit.di.component.RepositoryComponent
import com.anymore.mvvmkit.mvvm.lifecycle.activity.ActivityLifecycle
import com.anymore.mvvmkit.repository.RepositoryInjector

/**
 * Created by liuyuanmao on 2019/3/11.
 */

interface IApplicationLifecycle{
    fun attachBaseContext()
    fun onCreate()
    fun onTerminate()
    fun getRepositoryComponent():RepositoryComponent
}

class ApplicationWrapper(private val mApplication: Application):IApplicationLifecycle{

    private val mRepositoryInjector by lazy { RepositoryInjector(mApplication) }
    private val mActivityLifecycleCallbacks = ActivityLifecycle()

    override fun attachBaseContext() {
        mRepositoryInjector.onCreate()
    }

    override fun onCreate() {
        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    override fun onTerminate() {
        mApplication.unregisterActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    override fun getRepositoryComponent()=mRepositoryInjector.getRepositoryComponent()

}