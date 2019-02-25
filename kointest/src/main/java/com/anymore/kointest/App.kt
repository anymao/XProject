package com.anymore.kointest

import android.app.Application
import com.anymore.kointest.di.appModule
import org.koin.android.ext.android.startKoin

/**
 * Created by liuyuanmao on 2019/2/25.
 */
class App: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}