package com.anymore.example.mvvm

import android.content.Context
import com.anymore.example.di.DaggerExampleAppComponent
import com.anymore.mvvmkit.mvvm.lifecycle.KitApplication
import com.anymore.example.di.ExampleAppModule

/**
 * Created by liuyuanmao on 2019/3/11.
 */
class ExampleApplication:KitApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerExampleAppComponent.builder()
            .exampleAppModule(ExampleAppModule(this))
            .build()
            .inject(this)
    }
}