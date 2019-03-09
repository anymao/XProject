package com.anymore.mvvmkit.di.component

import com.anymore.mvvmkit.di.module.HttpClientModule
import com.anymore.mvvmkit.repository.IRepositoryManager
import dagger.Component
import okhttp3.OkHttpClient

/**
 * Created by liuyuanmao on 2019/3/9.
 */
@Component(modules = [HttpClientModule::class])
interface RepositoryComponent{
    fun getRepository():IRepositoryManager
    fun okHttpClient():OkHttpClient
    fun inject()
}