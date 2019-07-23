package com.anymore.mvpkit.di.component

import com.anymore.mvpkit.di.module.AppModule
import com.anymore.mvpkit.mvp.lifecycle.KitApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by liuyuanmao on 2019/2/23.
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class,AndroidSupportInjectionModule::class, AppModule::class])
internal interface KitComponent {
    fun inject(kitApplication: KitApplication)
}