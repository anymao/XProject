package com.anymore.mvvmkit.utils

import android.content.Context
import com.anymore.mvvmkit.di.module.RepositoryConfigsModule

/**
 * Created by liuyuanmao on 2019/3/11.
 */
object ManifestParser {
    /**
     * 从Manifest 的mate-data中读取配置
     */
    fun parseRepositoryConfig(context: Context):List<RepositoryConfigsModule.RepositoryConfig>{
        return emptyList()
    }
}