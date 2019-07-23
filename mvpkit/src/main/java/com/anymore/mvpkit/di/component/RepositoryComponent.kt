package com.anymore.mvpkit.di.component

import com.anymore.mvpkit.di.module.HttpClientModule
import com.anymore.mvpkit.di.module.RepositoryConfigsModule
import com.anymore.mvpkit.di.module.RepositoryModule
import com.anymore.mvpkit.repository.IRepositoryManager
import com.anymore.mvpkit.repository.RepositoryInjector
import com.google.gson.Gson
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by liuyuanmao on 2019/3/9.
 */

@Singleton
@Component(modules = [RepositoryModule::class, RepositoryConfigsModule::class, HttpClientModule::class])
interface RepositoryComponent{
    fun getRepository(): IRepositoryManager
    fun okHttpClient():OkHttpClient
    fun gson():Gson
    fun inject(injector: RepositoryInjector)
}