package com.anymore.simple.mvvm

import android.content.Context
import com.anymore.mvvmkit.mvvm.lifecycle.BaseApplication
import com.anymore.simple.BuildConfig
import com.anymore.simple.di.AppModule
import com.anymore.simple.di.DaggerAppComponent
import timber.log.Timber

/**
 * Created by liuyuanmao on 2019/2/21.
 */
class KitApplication:BaseApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}