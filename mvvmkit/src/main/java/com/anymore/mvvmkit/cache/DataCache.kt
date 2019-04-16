package com.anymore.mvvmkit.cache

import java.lang.RuntimeException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Created by liuyuanmao on 2019/4/16.
 */
class DataCache private constructor(private val mMemoryCache: MemoryCache?,private val mDiskCache: DiskCache?){
    
    companion object {
        fun builder() = Builder()
    }

    class Builder {
        var mMemoryCache:MemoryCache? = null
        var mDiskCache:DiskCache? = null
        fun build() = DataCache(mMemoryCache,mDiskCache)
    }
}

@ExperimentalContracts
private inline fun <reified T> checkNotNull(value:T?, message:String = "the value is null"){
    contract {
        returns() implies(value != null)
    }
    if (value == null){
        throw RuntimeException(message)
    }
}