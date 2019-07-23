package com.anymore.client

import android.content.Context
import com.anymore.client.di.component.DaggerAppComponent
import com.anymore.client.di.module.AppModule
import com.anymore.mvpkit.mvp.lifecycle.KitApplication

/**
 * Created by liuyuanmao on 2019/7/23.
 */
class App : KitApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }
}