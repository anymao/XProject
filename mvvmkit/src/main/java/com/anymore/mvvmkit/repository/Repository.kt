package com.anymore.mvvmkit.repository

import android.support.v4.util.LruCache
import android.util.SparseArray
import dagger.Lazy
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/2/28.
 */

interface IRepositoryManager{
    fun<T> obtainRetrofitService(key:Int,retrofitClass:Class<T>):T
}

class RepositoryManager @Inject constructor(private val mRetrofits:Lazy<SparseArray<Retrofit>>)
    :IRepositoryManager{

    private val mRetrofitCache by lazy { LruCache<String,Any?>(500) }

    override fun <T> obtainRetrofitService(key: Int, retrofitClass: Class<T>): T {
        var result = mRetrofitCache.get(retrofitClass.name)
        if (result == null){
            result = mRetrofits.get()[key].create(retrofitClass)
            mRetrofitCache.put(retrofitClass.name,result!!)
        }
        @Suppress("UNCHECKED_CAST")
        return result as T
    }
}