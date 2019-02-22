package com.anymore.simple.mvvm

import android.content.Context
import com.anymore.mvvmkit.mvvm.lifecycle.BaseApplication
import com.anymore.simple.di.AppModule
import com.anymore.simple.di.DaggerAppComponent

/**
 * Created by liuyuanmao on 2019/2/21.
 */
class KitApplication:BaseApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
    }
}