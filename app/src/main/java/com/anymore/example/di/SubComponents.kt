package com.anymore.example.di

import com.anymore.example.mvvm.view.login.LoginActivity
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.example.mvvm.view.main.MainActivity
import com.anymore.example.mvvm.view.register.RegisterActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by liuyuanmao on 2019/3/11.
 */
@ActivityScope
@Subcomponent(modules = [MainActivityViewModelModule::class,MainModelModule::class])
interface MainActivitySubComponent: AndroidInjector<MainActivity>{

    @Subcomponent.Builder
    abstract class Builder:AndroidInjector.Builder<MainActivity>()
}

@ActivityScope
@Subcomponent(modules = [RegisterActivityViewModelModule::class,UserModelModule::class])
interface RegisterActivitySubComponent:AndroidInjector<RegisterActivity>{

    @Subcomponent.Builder
    abstract class Builder:AndroidInjector.Builder<RegisterActivity>()
}

@ActivityScope
@Subcomponent(modules = [LoginActivityViewModelModule::class,UserModelModule::class])
interface LoginActivitySubComponent:AndroidInjector<LoginActivity>{

    @Subcomponent.Builder
    abstract class Builder:AndroidInjector.Builder<LoginActivity>()
}