package com.anymore.simple.di

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.ViewModel
import com.anymore.mvvmkit.di.key.ViewModelKey
import com.anymore.mvvmkit.di.module.ViewModelFactoryModule
import com.anymore.simple.mvvm.MainActivity
import com.anymore.simple.mvvm.MainActivityViewModel
import com.anymore.simple.mvvm.MainModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by liuyuanmao on 2019/3/11.
 */
@Module(includes = [ViewModelFactoryModule::class,MainActivityModule::class])
class SimpleAppModule(private val application: Application){

    @Provides
    fun provideApplication()=application
}

@Module
abstract class MainActivityViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun provideMainActivityViewModel(viewModel:MainActivityViewModel):ViewModel
}

@Module(subcomponents = [MainActivitySubComponent::class])
abstract class MainActivityModule{

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindActivityInjectorFactory(builder: MainActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>

}

@Module
class MainModelModule{

    @Provides
    fun provideMainModel(model:MainModel)= model
}