package com.anymore.mvvmkit.mvvm.lifecycle.application

import android.app.Application
import android.support.multidex.MultiDex
import com.anymore.mvvmkit.BuildConfig
import com.anymore.mvvmkit.di.component.RepositoryComponent
import com.anymore.mvvmkit.mvvm.lifecycle.activity.ActivityLifecycle
import com.anymore.mvvmkit.repository.RepositoryInjector
import com.facebook.stetho.Stetho
import timber.log.Timber

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
    private val mActivityLifecycle = ActivityLifecycle()
    override fun attachBaseContext() {
        MultiDex.install(mApplication)
        mRepositoryInjector.onCreate()
    }

    override fun onCreate() {
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(mApplication)
        }
        mActivityLifecycle.install(mApplication)
    }

    override fun onTerminate() {
        mActivityLifecycle.uninstall(mApplication)
    }

    override fun getRepositoryComponent()=mRepositoryInjector.getRepositoryComponent()

}