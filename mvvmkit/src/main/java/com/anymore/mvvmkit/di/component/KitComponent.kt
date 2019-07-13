package com.anymore.mvvmkit.di.component

import com.anymore.mvvmkit.di.module.KitModule
import com.anymore.mvvmkit.mvvm.lifecycle.KitApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by liuyuanmao on 2019/2/23.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class,AndroidSupportInjectionModule::class, KitModule::class])
internal interface KitComponent {
    fun inject(kitApplication: KitApplication)
}