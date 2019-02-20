package com.anymore.mvvmkit.mvvm.lifecycle

import android.app.Application

/**
 * Created by liuyuanmao on 2019/2/20.
 */
class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ActivityLifecycle())
    }
}