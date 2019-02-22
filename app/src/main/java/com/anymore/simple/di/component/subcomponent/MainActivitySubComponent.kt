package com.anymore.simple.di.component.subcomponent

import com.anymore.simple.mvvm.view.MainActivity
import com.anymore.simple.di.module.MainViewModelModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by liuyuanmao on 2019/2/21.
 */
@Subcomponent(modules = [MainViewModelModule::class])
interface MainActivitySubComponent :AndroidInjector<MainActivity>{

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MainActivity>()
}