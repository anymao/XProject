package com.anymore.mvvmkit.di.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.anymore.mvvmkit.mvvm.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by liuyuanmao on 2019/2/23.
 */
@Module
class KitModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication() = application
}

@Module
interface ViewModelFactoryModule {
    @Binds
    fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}