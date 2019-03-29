package com.anymore.example.app

import android.content.Context
import com.anymore.example.mvvm.model.api.BASE_URL
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.mvvmkit.di.module.RepositoryConfigsModule
import timber.log.Timber

/**
 * Created by liuyuanmao on 2019/3/12.
 */
class ExampleRepositoryConfigConfig:RepositoryConfigsModule.RepositoryConfig {
    override fun applyConfig(context: Context, builder: RepositoryConfigsModule.Builder) {
        Timber.d("applyConfig ....start")
        builder.apply {
            addUrl(KEY, BASE_URL)
        }
        Timber.d("applyConfig ....end")
    }
}