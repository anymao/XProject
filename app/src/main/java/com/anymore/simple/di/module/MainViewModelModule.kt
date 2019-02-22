package com.anymore.simple.di.module

import android.arch.lifecycle.ViewModel
import com.anymore.mvvmkit.di.key.ViewModelKey
import com.anymore.simple.mvvm.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by liuyuanmao on 2019/2/21.
 */
@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideViewModel(mainViewModel: MainViewModel):ViewModel
}