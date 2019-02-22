package com.anymore.simple.di.module

import android.app.Activity
import com.anymore.mvvmkit.di.module.ViewModelFactoryModule
import com.anymore.simple.mvvm.view.MainActivity
import com.anymore.simple.di.component.subcomponent.MainActivitySubComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by liuyuanmao on 2019/2/21.
 */
@Module(subcomponents = [MainActivitySubComponent::class])
abstract class MainActivityModule{

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindActivityInjectorFactory(builder:MainActivitySubComponent.Builder):AndroidInjector.Factory<out Activity>
}