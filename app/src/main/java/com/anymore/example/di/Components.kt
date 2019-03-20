package com.anymore.example.di

import com.anymore.mvvmkit.di.scope.ApplicationScope
import com.anymore.example.mvvm.ExampleApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by liuyuanmao on 2019/3/11.
 */
@ApplicationScope
@Component(modules = [AndroidSupportInjectionModule::class,ExampleAppModule::class])
interface ExampleAppComponent{
    fun inject(application: ExampleApplication)
}