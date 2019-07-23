package com.anymore.client.di.module

import com.anymore.client.mvp.login.LoginActivity
import com.anymore.mvpkit.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by liuyuanmao on 2019/7/23.
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ModelsModule::class,PresentersModule::class,ViewsModule::class])
    abstract fun contributeLoginActivity():LoginActivity
}