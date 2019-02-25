package com.anymore.kointest.di

import com.anymore.kointest.MainActivityViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by liuyuanmao on 2019/2/25.
 */
val appModule = module {
    single<HelloRepository> { HelloRepositoryImpl() }
    viewModel { MainActivityViewModel(get()) }
}