package com.anymore.mvvmkit.mvvm.lifecycle

import android.app.Activity
import android.app.Application
import android.content.Context
import com.anymore.mvvmkit.di.component.DaggerAppComponent
import com.anymore.mvvmkit.mvvm.lifecycle.activity.ActivityLifecycle
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/2/20.
 */
open class BaseApplication:Application(),HasActivityInjector{

    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerAppComponent.create().inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityLifecycle())
    }

    override fun activityInjector()=mActivityInjector

}