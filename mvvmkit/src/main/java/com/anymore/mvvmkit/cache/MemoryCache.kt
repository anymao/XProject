package com.anymore.mvvmkit.cache

import android.support.v4.util.LruCache

/**
 * Created by liuyuanmao on 2019/4/16.
 */
interface MemoryCache{
    fun store(key:String,value:Any)
    fun <T>get(key: String):T?
    fun <T>remove(key: String):T?
    fun clear()
}

/**
 * LRU内存缓存实现
 */
class LruMemoryCache(private val maxSize: Int):MemoryCache{

    private val mCache by lazy { LruCache<String,Any>(maxSize) }

    override fun store(key: String, value: Any) {
        mCache.put(key,value)
    }

    override fun <T> get(key: String): T? {
        @Suppress("UNCHECKED_CAST")
        return mCache.get(key) as T?
    }

    override fun <T> remove(key: String): T? {
        @Suppress("UNCHECKED_CAST")
        return mCache.remove(key) as T?
    }

    override fun clear() {
        mCache.evictAll()
    }

}
