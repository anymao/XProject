package com.anymore.simple.di


import com.anymore.simple.mvvm.KitApplication
import dagger.Component

/**
 * Created by liuyuanmao on 2019/2/21.
 */
@Component(modules = [AppModule::class, MainActivityModule::class, ViewModelFactoryModule::class])
interface AppComponent{
    fun inject(application:KitApplication)
}
