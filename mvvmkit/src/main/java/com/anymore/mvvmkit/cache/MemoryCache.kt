package com.anymore.mvvmkit.cache

import android.support.v4.util.LruCache

/**
 * Created by liuyuanmao on 2019/3/12.
 */
class MemoryCache(private val maxSize:Int):ICache {

    private val mCache by lazy { LruCache<String,Any>(maxSize) }

    override fun store(key: String, value: Any): Boolean {
        return mCache.put(key,value) != null
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String): T? {
        return mCache[key] as T?
    }

    override fun remove(key: String): Boolean {
        return mCache.remove(key) != null
    }

    override fun clear() {
        mCache.evictAll()
    }

    override fun isContain(key: String): Boolean {
        return mCache.get(key) != null
    }
}