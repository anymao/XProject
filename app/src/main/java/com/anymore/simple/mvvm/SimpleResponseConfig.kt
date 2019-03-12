package com.anymore.simple.mvvm

import android.content.Context
import com.anymore.mvvmkit.di.module.RepositoryConfigsModule

/**
 * Created by liuyuanmao on 2019/3/12.
 */
class SimpleResponseConfig:RepositoryConfigsModule.RepositoryConfig {
    override fun applyConfig(context: Context, builder: RepositoryConfigsModule.Builder) {
        builder.apply {
            addUrl(KEY, BASE_URL)
        }
    }
}