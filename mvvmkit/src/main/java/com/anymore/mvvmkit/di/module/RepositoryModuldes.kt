package com.anymore.mvvmkit.di.module

import android.content.Context
import com.anymore.mvvmkit.repository.remote.GsonConfig
import com.anymore.mvvmkit.repository.remote.OkHttpConfig
import com.anymore.mvvmkit.repository.remote.RetrofitConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by liuyuanmao on 2019/3/7.
 */
//////////////////***************************////////////////////
//////////////////    Remote repository      ////////////////////
//////////////////***************************////////////////////

@Singleton
@Module(includes = [RetrofitModule::class,OkHttpClientModule::class,GsonModule::class])
class RemoteRepositoryModule private constructor(builder: Builder){

    private val mOkHttpConfig: OkHttpConfig?
    private val mGsonConfig: GsonConfig?
    private val mRetrofitConfig: RetrofitConfig?

    init {
        mGsonConfig = builder.gsonConfig
        mOkHttpConfig = builder.okHttpConfig
        mRetrofitConfig = builder.retrofitConfig
    }





    private class Builder {
        var okHttpConfig: OkHttpConfig? = null
        var gsonConfig: GsonConfig? = null
        var retrofitConfig: RetrofitConfig? = null

        fun setOkHttpConfig(okHttpConfig: OkHttpConfig): Builder {
            this.okHttpConfig = okHttpConfig
            return this
        }

        fun setGsonConfig(gsonConfig: GsonConfig): Builder {
            this.gsonConfig = gsonConfig
            return this
        }

        fun setRetrofitConfig(retrofitConfig: RetrofitConfig): Builder {
            this.retrofitConfig = retrofitConfig
            return this
        }

        fun build(): RemoteRepositoryModule {
            return RemoteRepositoryModule(this)
        }
    }

}

@Module(includes = [OkHttpClientModule::class,GsonModule::class])
class RetrofitModule(private val config: RetrofitConfig?){
    @Provides
    fun provideRetrofitBuilder()= Retrofit.Builder()

    @Provides
    fun provideConfig()=config

    @Provides
    fun provideRetrofit(context: Context, builder: Retrofit.Builder, config: RetrofitConfig?): Retrofit {
        config?.applyConfig(context,builder)
        return builder.build()
    }
}

@Module
class OkHttpClientModule(private val config: OkHttpConfig?){

    @Provides
    fun provideOkHttpClientBuilder()= OkHttpClient.Builder()

    @Provides
    fun provideConfig()=config

    @Provides
    fun provideOkHttpClient(context: Context, builder: OkHttpClient.Builder, config: OkHttpConfig?): OkHttpClient {
        config?.applyConfig(context,builder)
        return builder.build()
    }

}

@Module
class GsonModule(private val config: GsonConfig?){
    @Provides
    fun provideGsonBuilder()= GsonBuilder()

    @Provides
    fun provideConfig()=config

    @Provides
    fun provideGson(context: Context, builder: GsonBuilder, config: GsonConfig?): Gson {
        config?.applyConfig(context,builder)
        return builder.create()
    }

}
