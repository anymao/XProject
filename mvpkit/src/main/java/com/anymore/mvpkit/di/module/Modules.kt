package com.anymore.mvpkit.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by liuyuanmao on 2019/2/23.
 */
@Module
internal class KitApplicationModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication() = application
}