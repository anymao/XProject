package com.anymore.mvvmkit.repository

import android.app.Application
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.support.v4.util.LruCache
import android.util.SparseArray
import com.anymore.mvvmkit.di.module.RepositoryConfigsModule
import dagger.Lazy
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/2/28.
 */

interface IRepositoryManager{
    fun<T> obtainRetrofitService(key:Int,retrofitClass:Class<T>):T
    fun<DB : RoomDatabase> obtainRoomDatabase(databaseClass: Class<DB>,databaseName:String):DB
}

class RepositoryManager @Inject constructor(private val mApplication: Application,
                                            private val mRetrofits:Lazy<SparseArray<Retrofit>>,
                                            private val mRoomDatabaseConfig: RepositoryConfigsModule.RoomDatabaseConfig)
    :IRepositoryManager{

    private val mRetrofitCache by lazy { LruCache<String,Any>(500) }
    private val mRoomDatabaseCache by lazy { LruCache<String,Any>(500) }

    override fun <T> obtainRetrofitService(key: Int, retrofitClass: Class<T>): T {
        var result = mRetrofitCache.get(retrofitClass.name)
        if (result == null){
            result = mRetrofits.get().get(key).create(retrofitClass)
            mRetrofitCache.put(retrofitClass.name,result!!)
        }
        @Suppress("UNCHECKED_CAST")
        return result as T
    }

    override fun <DB : RoomDatabase> obtainRoomDatabase(databaseClass: Class<DB>, databaseName: String): DB {
        var result = mRoomDatabaseCache.get(databaseClass.name)
        if (result == null){
            val builder = Room.databaseBuilder(mApplication,databaseClass,databaseName)
            mRoomDatabaseConfig.config(mApplication,builder)
            result = builder.build()
            mRoomDatabaseCache.put(databaseClass.name,result)
        }
        @Suppress("UNCHECKED_CAST")
        return result as DB
    }
}