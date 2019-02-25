package com.anymore.simple.di

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.ViewModel
import com.anymore.mvvmkit.di.key.ViewModelKey
import com.anymore.mvvmkit.di.module.ViewModelFactoryModule
import com.anymore.simple.mvvm.view.MainActivity
import com.anymore.simple.mvvm.viewmodel.MainViewModel
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
    @ViewModelKey(MainViewModel::class)
    abstract fun provideViewModel(mainViewModel: MainViewModel): ViewModel
}