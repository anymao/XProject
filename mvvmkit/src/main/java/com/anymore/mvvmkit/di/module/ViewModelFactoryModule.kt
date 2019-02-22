package com.anymore.mvvmkit.di.module

import android.arch.lifecycle.ViewModelProvider
import com.anymore.mvvmkit.mvvm.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by liuyuanmao on 2019/2/21.
 */
@Module
interface ViewModelFactoryModule {
    @Binds
    fun provideViewModelFactory(viewModelFactory: ViewModelFactory):ViewModelProvider.Factory
}