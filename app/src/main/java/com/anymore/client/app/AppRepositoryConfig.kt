package com.anymore.client.app

import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.anymore.client.BuildConfig
import com.anymore.client.app.cookies.PersistentCookieJar
import com.anymore.client.app.cookies.SharedPreferencesCookieStore
import com.anymore.client.app.interceptor.CacheControlInterceptor
import com.anymore.client.app.interceptor.HeadersInterceptor
import com.anymore.client.repository.remote.BASE_URL
import com.anymore.client.repository.remote.KEY
import com.anymore.mvpkit.di.module.RepositoryConfigsModule
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File

/**
 * Created by liuyuanmao on 2019/7/23.
 */
class AppRepositoryConfig :RepositoryConfigsModule.RepositoryConfig {
    companion object {
        const val MAX_CACHE_SIZE = 30 * 1024 * 1024L
    }

    override fun applyConfig(context: Context, builder: RepositoryConfigsModule.Builder) {
        Timber.d("applyConfig ....start")
        builder.apply {
            addUrl(KEY, BASE_URL)
            okHttpConfig = object : RepositoryConfigsModule.OkHttpConfig {

                override fun applyConfig(context: Context, builder: OkHttpClient.Builder) {
                    builder.cache(provideOkCache(context, MAX_CACHE_SIZE))
                    val cookieStore = SharedPreferencesCookieStore(context)
                    builder.cookieJar(PersistentCookieJar(cookieStore))
                    val okLogger = HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    }
                    builder.addNetworkInterceptor(HeadersInterceptor())//为请求添加Headers
                        .addNetworkInterceptor(CacheControlInterceptor(context))
                        .addNetworkInterceptor(okLogger)
                }

            }
            roomDatabaseConfig = object : RepositoryConfigsModule.RoomDatabaseConfig {
                override fun <DB : RoomDatabase> config(
                    context: Context,
                    builder: RoomDatabase.Builder<*>,
                    databaseClass: Class<DB>,
                    databaseName: String
                ) {

                }

            }
        }
        Timber.d("applyConfig ....end")
    }


    private fun provideOkCache(context: Context, maxSize: Long): Cache {
        val cacheDir = File(context.cacheDir, "okCache")
        return Cache(cacheDir, maxSize)
    }
}