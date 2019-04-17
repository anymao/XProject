package com.anymore.mvvmkit.cache

import android.support.annotation.IntDef
import java.io.Serializable


/**
 * Created by liuyuanmao on 2019/4/16.
 */
class DataCache private constructor(private val mMemoryCache: MemoryCache,private val mDiskCache: DiskCache){
    
    companion object {

        const val MEMORY = 0x01
        const val DISK = 0x10
        const val MEMORY_DISK = 0x11

        fun builder() = Builder()
    }

    fun put(key:String,value:Any,@CacheStrategy strategy: Int = MEMORY){
        if (strategy and MEMORY != 0){
            mMemoryCache.store(key,value)
        }
        if (strategy and DISK != 0){
            require(value is Serializable){
                "the value you put is not Serializable!"
            }
            mDiskCache.store(key,value)
        }
    }

    fun <T>get(key: String,@CacheStrategy strategy: Int = MEMORY):T?{
        var result:T? = null
        if (strategy and MEMORY != 0){
            result = mMemoryCache.get(key)
        }
        if (result == null && strategy and DISK != 0){
            result = mDiskCache.get(key)
        }
        return result
    }

    fun <T>remove(key: String,@CacheStrategy strategy: Int = MEMORY_DISK):T?{
        var result:T? = null
        if (strategy and MEMORY != 0){
            result = mMemoryCache.remove(key)
        }
        if (strategy and DISK != 0){
            result = mDiskCache.remove(key)
        }
        return result
    }


    class Builder {
        var mMemoryCache:MemoryCache? = null
        var mDiskCache:DiskCache? = null

        fun build() = DataCache(mMemoryCache!!,mDiskCache!!)
    }

    @IntDef(value = [MEMORY, DISK, MEMORY_DISK],flag = true)
    @Retention(AnnotationRetention.SOURCE)
    annotation class CacheStrategy
}
