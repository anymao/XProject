package com.anymore.mvvmkit.cache

import android.text.TextUtils
import com.anymore.mvvmkit.utils.Preconditions
import com.jakewharton.disklrucache.DiskLruCache
import timber.log.Timber
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable
import java.lang.Exception


/**
 * Created by liuyuanmao on 2019/3/12.
 */
interface IDiskCache{
    fun store(key: String,value: Serializable):Boolean
    fun<T:Serializable> get(key: String):T?
    fun remove(key: String):Boolean
    fun clear()
    fun isContain(key: String):Boolean
}

class DiskCache(private val cacheFile:File):ICache {

    private val mCache by lazy { DiskLruCache.open(cacheFile,1,1, Long.MAX_VALUE) }

    override fun store(key: String, value: Any): Boolean {
        Preconditions.checkState(TextUtils.isEmpty(key),"key must not be null")
        Preconditions.checkState(value !is  Serializable,"your value Class<${value.javaClass.name}> must implement Serializable")
        var result: Boolean
        val editor = mCache.edit(key)
        var oos:ObjectOutputStream? = null
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
            oos?.close()
        }
        return result
    }

    override fun <T> get(key: String): T? {
        var result:T? = null
        var ois:ObjectInputStream? = null
        try {
            ois = ObjectInputStream(mCache[key].getInputStream(0))
            @Suppress("UNCHECKED_CAST")
            result = ois.readObject() as T
        }catch (e:Exception){
            Timber.e(e)
        }finally {
            ois?.close()
            mCache[key].close()
        }
        return result
    }

    override fun remove(key: String): Boolean {
        return mCache.remove(key)
    }

    override fun clear() {
        mCache.delete()
    }

    override fun isContain(key: String): Boolean {
        var result: Boolean
        try {
            result = mCache.get(key) != null
        }catch (e:Exception){
            result = false
            Timber.e(e)
        }
        return result
    }

}