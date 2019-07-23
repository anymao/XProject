package com.anymore.client.di.module

import com.anymore.client.mvp.login.LoginActivity
import com.anymore.client.mvp.login.LoginContract
import dagger.Binds
import dagger.Module

/**
 * Created by liuyuanmao on 2019/7/23.
 */
@Module
abstract class ViewsModule {

    @Binds
    abstract fun provideLoginView(loginActivity: LoginActivity):LoginContract.IView
}