package com.anymore.client.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by liuyuanmao on 2019/7/23.
 */
@Module(includes = [ActivityBindingModule::class,FragmentBindingModule::class])
class AppModule (private val application: Application){

    @Singleton
    @Provides
    fun provideApplication()=application
}