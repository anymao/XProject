package com.anymore.mvvmkit.cache

/**
 * Created by liuyuanmao on 2019/3/12.
 */
interface ICache {
    fun store(key:String,value:Any):Boolean
    fun <T>get(key: String):T?
    fun remove(key: String):Boolean
    fun clear()
    fun isContain(key: String):Boolean
}