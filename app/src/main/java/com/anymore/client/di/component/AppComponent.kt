package com.anymore.client.di.component

import com.anymore.client.App
import com.anymore.client.di.module.AppModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by liuyuanmao on 2019/7/23.
 */
@Component(modules = [AndroidSupportInjectionModule::class,AppModule::class])
interface AppComponent {
    fun inject(app: App)
}