package com.anymore.mvvmkit.mvvm.lifecycle

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.v4.app.Fragment
import com.anymore.mvvmkit.di.component.DaggerAppComponent
import com.anymore.mvvmkit.mvvm.lifecycle.application.ApplicationWrapper
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/2/20.
 */
open class KitApplication:Application(),HasActivityInjector,HasSupportFragmentInjector{


    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    private val mApplicationWrapper by lazy { ApplicationWrapper(this) }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerAppComponent.create().inject(this)
        mApplicationWrapper.attachBaseContext()
    }

    override fun onCreate() {
        super.onCreate()
        mApplicationWrapper.onCreate()
    }

    override fun onTerminate() {
        mApplicationWrapper.onTerminate()
        super.onTerminate()
    }

    override fun activityInjector()=mActivityInjector

    override fun supportFragmentInjector()=mFragmentInjector

    fun getRepositoryComponent()=mApplicationWrapper.getRepositoryComponent()
}