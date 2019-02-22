package com.anymore.kotlindaggertest.di

import com.anymore.kotlindaggertest.APP
import dagger.Component

/**
 * Created by liuyuanmao on 2019/2/22.
 */
@Component(modules = [AppModule::class,ViewModelFactoryModule::class,MainActivityModule::class])
interface AppComponent{
    fun inject(app:APP)
}