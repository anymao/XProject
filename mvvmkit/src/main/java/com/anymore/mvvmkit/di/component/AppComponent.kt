package com.anymore.mvvmkit.di.component

import com.anymore.mvvmkit.di.module.AppModule
import com.anymore.mvvmkit.mvvm.lifecycle.BaseApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by liuyuanmao on 2019/2/21.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class,AppModule::class])
interface AppComponent {
    fun inject(baseApplication: BaseApplication)
}