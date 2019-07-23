package com.anymore.mvpkit.mvp.lifecycle.application

import android.app.Application
import android.support.multidex.MultiDex
import com.anymore.mvpkit.di.component.RepositoryComponent
import com.anymore.mvpkit.mvp.lifecycle.activity.ActivityLifecycle
import com.anymore.mvpkit.repository.RepositoryInjector

/**
 * Created by liuyuanmao on 2019/3/11.
 */

interface IApplicationLifecycle{
    fun attachBaseContext()
    fun onCreate()
    fun onTerminate()
    fun getRepositoryComponent(): RepositoryComponent
}

class ApplicationWrapper(private val mApplication: Application): IApplicationLifecycle {

    private val mRepositoryInjector by lazy { RepositoryInjector(mApplication) }
    private val mActivityLifecycle = ActivityLifecycle()
    override fun attachBaseContext() {
        MultiDex.install(mApplication)
        mRepositoryInjector.onCreate()
    }

    override fun onCreate() {
        mActivityLifecycle.install(mApplication)
    }

    override fun onTerminate() {
        mActivityLifecycle.uninstall(mApplication)
    }

    override fun getRepositoryComponent()=mRepositoryInjector.getRepositoryComponent()

}