package com.anymore.simple.mvvm

import android.content.Context
import com.anymore.mvvmkit.mvvm.lifecycle.BaseApplication
import com.anymore.simple.di.DaggerSimpleAppComponent
import com.anymore.simple.di.SimpleAppModule

/**
 * Created by liuyuanmao on 2019/3/11.
 */
class SimpleApplication:BaseApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerSimpleAppComponent.builder().simpleAppModule(SimpleAppModule(this)).build()
    }
}