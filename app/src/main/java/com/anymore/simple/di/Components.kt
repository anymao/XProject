package com.anymore.simple.di

import com.anymore.mvvmkit.di.scope.ApplicationScope
import com.anymore.simple.mvvm.SimpleApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by liuyuanmao on 2019/3/11.
 */
@ApplicationScope
@Component(modules = [AndroidSupportInjectionModule::class,SimpleAppModule::class])
interface SimpleAppComponent{
    fun inject(application: SimpleApplication)
}