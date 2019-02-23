package com.anymore.simple.di

import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.simple.mvvm.view.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by liuyuanmao on 2019/2/21.
 */
@ActivityScope
@Subcomponent(modules = [MainViewModelModule::class])
interface MainActivitySubComponent :AndroidInjector<MainActivity>{

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<MainActivity>()
}