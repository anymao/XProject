package com.anymore.client.di.module

import android.app.Application
import dagger.Module
import dagger.Provides

/**
 * Created by liuyuanmao on 2019/7/23.
 */
@Module(includes = [ActivityBindingModule::class,FragmentBindingModule::class])
class AppModule (private val application: Application){

    @Provides
    fun provideApplication()=application
}