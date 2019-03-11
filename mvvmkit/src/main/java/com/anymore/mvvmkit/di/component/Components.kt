package com.anymore.mvvmkit.di.component

import com.anymore.mvvmkit.di.module.AppModule
import com.anymore.mvvmkit.mvvm.lifecycle.KitApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by liuyuanmao on 2019/2/23.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class,AndroidSupportInjectionModule::class, AppModule::class])
internal interface AppComponent {
    fun inject(kitApplication: KitApplication)
}