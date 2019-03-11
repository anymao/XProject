package com.anymore.simple.mvvm

import android.content.Context
import com.anymore.mvvmkit.mvvm.lifecycle.KitApplication
import com.anymore.simple.di.DaggerSimpleAppComponent
import com.anymore.simple.di.SimpleAppModule

/**
 * Created by liuyuanmao on 2019/3/11.
 */
class SimpleApplication:KitApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerSimpleAppComponent.builder()
            .simpleAppModule(SimpleAppModule(this))
            .build()
            .inject(this)
    }
}