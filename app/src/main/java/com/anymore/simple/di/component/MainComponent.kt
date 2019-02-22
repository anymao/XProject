package com.anymore.simple.di.component

import com.anymore.simple.di.module.MainActivityModule
import com.anymore.simple.mvvm.view.MainActivity
import dagger.Component
import dagger.android.AndroidInjectionModule

/**
 * Created by liuyuanmao on 2019/2/21.
 */
@Component(modules = [MainActivityModule::class,AndroidInjectionModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}