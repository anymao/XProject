package com.anymore.mvvmkit.cache

import com.jakewharton.disklrucache.DiskLruCache
import timber.log.Timber
import java.io.*

/**
 * Created by liuyuanmao on 2019/4/16.
 */

/**
 * 磁盘缓存
 */
interface DiskCache{
    fun store(key:String,value:Serializable):Boolean
    fun<T> get(key: String):T?
    fun<T> remove(key: String):T?
    fun clear()
}

class LruDiskCache private constructor(private val mCacheDir:File):DiskCache{

    private val mDiskCache by lazy { DiskLruCache.open(mCacheDir,1,1,1) }

    override fun store(key: String, value: Serializable):Boolean {
        var result: Boolean
        val editor = mDiskCache.edit(key)
        var oos: ObjectOutputStream? = null
        try {
            oos = ObjectOutputStream(editor.newOutputStream(0))
            oos.writeObject(value)
            oos.flush()
            editor.commit()
            oos.close()
            result = true
        }catch (e:Exception){
            Timber.e(e)
            result = false
        }finally {
            try {
                oos?.close()
            }catch (e:IOException){
                Timber.e(e)
            }
        }
        return result
    }

    override fun <T> get(key: String): T? {
        var result:T? = null
        var ois: ObjectInputStream? = null
        try {
            ois = ObjectInputStream(mDiskCache[key].getInputStream(0))
            @Suppress("UNCHECKED_CAST")
            result = ois.readObject() as T
        }catch (e:Exception){
            Timber.e(e)
        }finally {
            ois?.close()
            mDiskCache[key].close()
        }
        return result
    }

    override fun <T> remove(key: String): T? {
        val result:T?= get(key)
        return if (result != null){
            mDiskCache.remove(key)
            result
        }else{
            null
        }
    }

    override fun clear() {
        mDiskCache.delete()
    }

    class Builder(var cacheDir:String){
        var version = 1

        fun build():LruDiskCache = LruDiskCache(File(cacheDir))
    }
}