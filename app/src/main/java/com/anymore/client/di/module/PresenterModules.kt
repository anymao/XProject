package com.anymore.client.di.module

import com.anymore.client.ui.mvp.login.LoginContract
import com.anymore.client.ui.mvp.login.LoginPresenter
import com.anymore.client.ui.mvp.login.fragment.LoginPresenter2
import dagger.Binds
import dagger.Module

/**
 * 聚合所有的XXXPresenterModule的.kt文件
 * Created by liuyuanmao on 2019/7/23.
 */
@Module
abstract class LoginPresenterModule {

    @Binds
    abstract fun provideLoginPresenter(loginPresenter: LoginPresenter):LoginContract.IPresenter
}

@Module
abstract class LoginPresenter2Module{

    @Binds
    abstract fun provideLoginPresenter2(loginPresenter2: LoginPresenter2):LoginContract.IPresenter
}