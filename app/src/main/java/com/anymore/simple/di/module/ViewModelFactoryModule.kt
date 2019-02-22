package com.anymore.simple.di.module

import android.arch.lifecycle.ViewModelProvider
import com.anymore.simple.mvvm.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule{
    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}