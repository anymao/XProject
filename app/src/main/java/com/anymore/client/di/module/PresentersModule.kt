package com.anymore.client.di.module

import com.anymore.client.mvp.login.LoginContract
import com.anymore.client.mvp.login.LoginPresenter
import dagger.Binds
import dagger.Module

/**
 * Created by liuyuanmao on 2019/7/23.
 */
@Module
abstract class PresentersModule {

    @Binds
    abstract fun provideLoginPresenter(loginPresenter: LoginPresenter):LoginContract.IPresenter
}