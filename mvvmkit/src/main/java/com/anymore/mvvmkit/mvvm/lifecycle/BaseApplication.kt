package com.anymore.mvvmkit.mvvm.lifecycle

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.v4.app.Fragment
import com.anymore.mvvmkit.di.component.DaggerAppComponent
import com.anymore.mvvmkit.mvvm.lifecycle.activity.ActivityLifecycle
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/2/20.
 */
open class BaseApplication:Application(),HasActivityInjector,HasSupportFragmentInjector{


    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    private val mActivityLifecycleCallbacks by lazy { ActivityLifecycle() }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerAppComponent.create().inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    override fun onTerminate() {
        unregisterActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
        super.onTerminate()
    }

    override fun activityInjector()=mActivityInjector

    override fun supportFragmentInjector()=mFragmentInjector

}