package com.anymore.client.di.module

import com.anymore.client.ui.mvp.login.LoginActivity
import com.anymore.client.ui.mvp.login.LoginContract
import com.anymore.client.ui.mvp.login.fragment.LoginFragment
import com.anymore.mvpkit.di.scope.ActivityScope
import com.anymore.mvpkit.di.scope.FragmentScope
import dagger.Binds
import dagger.Module

/**
 * 聚合所有的XXXViewModule的.kt文件
 * Created by liuyuanmao on 2019/7/23.
 */
@Module
abstract class LoginActivityViewModule {

    @ActivityScope
    @Binds
    abstract fun provideLoginView(loginActivity: LoginActivity):LoginContract.IView
}

@Module
abstract class LoginFragmentViewModule{

    @FragmentScope
    @Binds
    abstract fun provideLoginView(loginFragment: LoginFragment):LoginContract.IView
}