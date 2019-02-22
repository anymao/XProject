package com.anymore.simple.di.module

import android.app.Application
import com.anymore.mvvmkit.di.module.ViewModelFactoryModule
import dagger.Module
import dagger.Provides

/**
 * Created by liuyuanmao on 2019/2/21.
 */
@Module(includes = [ViewModelFactoryModule::class,MainViewModelModule::class])
class AppModule(private val application: Application) {
    @Provides
    fun provideApplication()=application
}