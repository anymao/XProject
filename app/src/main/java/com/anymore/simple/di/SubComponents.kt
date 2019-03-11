package com.anymore.simple.di

import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.simple.mvvm.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by liuyuanmao on 2019/3/11.
 */
@ActivityScope
@Subcomponent(modules = [MainActivityViewModelModule::class])
interface MainActivitySubComponent: AndroidInjector<MainActivity>{

    @Subcomponent.Builder
    abstract class Builder:AndroidInjector.Builder<MainActivity>()
}