package com.anymore.kotlindaggertest.di

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.anymore.kotlindaggertest.MainActivity
import com.anymore.kotlindaggertest.MainActivityViewModel
import com.anymore.kotlindaggertest.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by liuyuanmao on 2019/2/22.
 */
@Module(includes = [ViewModelFactoryModule::class, MainViewModelModule::class])
class AppModule(private val application: Application) {
    @Provides
    fun provideApplication()=application
}

@Module(subcomponents = [MainActivitySubComponent::class])
abstract class MainActivityModule{

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindActivityInjectorFactory(builder: MainActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>
}

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun provideViewModel(mainViewModel: MainActivityViewModel): ViewModel
}

@Module
abstract class ViewModelFactoryModule{
    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}