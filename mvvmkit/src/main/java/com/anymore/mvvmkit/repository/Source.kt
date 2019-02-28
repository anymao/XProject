package com.anymore.mvvmkit.repository

/**
 * Created by liuyuanmao on 2019/2/28.
 */
/**
 * 数据分为本地数据源和远程数据源
 * 泛型S指代本地数据源Database或者是HttpClient
 */
interface Source<out S>{
    fun get():S
}