package com.anymore.example.app

import android.content.Context
import com.anymore.example.app.cookies.PersistentCookieJar
import com.anymore.example.app.cookies.SharedPreferencesCookieStore
import com.anymore.example.mvvm.model.api.BASE_URL
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.mvvmkit.BuildConfig
import com.anymore.mvvmkit.di.module.RepositoryConfigsModule
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File

/**
 * Created by liuyuanmao on 2019/3/12.
 */
class ExampleRepositoryConfig:RepositoryConfigsModule.RepositoryConfig {
    companion object {
        const val MAX_CACHE_SIZE = 30*1024*1024L
    }

    override fun applyConfig(context: Context, builder: RepositoryConfigsModule.Builder) {
        Timber.d("applyConfig ....start")
        builder.apply {
            addUrl(KEY, BASE_URL)
            okHttpConfig = object :RepositoryConfigsModule.OkHttpConfig{

                override fun applyConfig(context: Context, builder: OkHttpClient.Builder) {
                    builder.cache(provideOkCache(context, MAX_CACHE_SIZE))
                    val cookieStore = SharedPreferencesCookieStore(context)
                    builder.cookieJar(PersistentCookieJar(cookieStore))
//                    val cookieJar:CookieJar = PersistentCookieJar(SetCookieCache(),SharedPrefsCookiePersistor(context))
//                    builder.cookieJar(cookieJar)
                    val okLogger = HttpLoggingInterceptor()
                    if (BuildConfig.DEBUG){
                        okLogger.level = HttpLoggingInterceptor.Level.HEADERS
                    }else{
                        okLogger.level = HttpLoggingInterceptor.Level.NONE
                    }
                    builder.addInterceptor(okLogger)
                }

            }
        }
        Timber.d("applyConfig ....end")
    }


    private fun provideOkCache(context: Context,maxSize:Long):Cache{
        val cacheDir = File(context.cacheDir,"okCache")
        return Cache(cacheDir,maxSize)
    }
}