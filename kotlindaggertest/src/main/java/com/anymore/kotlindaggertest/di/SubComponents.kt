package com.anymore.kotlindaggertest.di

import com.anymore.kotlindaggertest.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by liuyuanmao on 2019/2/22.
 */

@Subcomponent(modules = [MainActivityModule::class,MainActivityViewModelModule::class])
interface MainActivitySubComponent :AndroidInjector<MainActivity>{

    @Subcomponent.Builder
    abstract class Builder :AndroidInjector.Builder<MainActivity>()
}