package com.anymore.kotlindaggertest.di

import com.anymore.kotlindaggertest.APP
import dagger.Component

/**
 * Created by liuyuanmao on 2019/2/22.
 */
@Component(modules = [AppModule::class, MainActivityModule::class, ViewModelFactoryModule::class])
interface AppComponent{
    fun inject(application:APP)
}