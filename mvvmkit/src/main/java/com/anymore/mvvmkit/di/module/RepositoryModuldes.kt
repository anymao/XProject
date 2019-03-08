package com.anymore.mvvmkit.di.module

import android.content.Context
import android.util.SparseArray
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by liuyuanmao on 2019/3/7.
 */
//////////////////***************************////////////////////
//////////////////    Remote repository      ////////////////////
//////////////////***************************////////////////////

@Module
class HttpClientModule private constructor(builder: Builder){

    private var mContext:Context?=null
    private var mRetrofitConfig:RetrofitConfig?=null
    private var mOkHttpConfig:OkHttpConfig?=null
    private var mGsonConfig:GsonConfig?=null

    init {
        this.mContext = builder.context
        this.mRetrofitConfig = builder.retrofitConfig
        this.mOkHttpConfig = builder.okHttpConfig
        this.mGsonConfig = builder.gsonConfig
    }

    @Provides
    fun provideRetrofitBuilder()= Retrofit.Builder()

    @Provides
    fun provideRetrofitConfig()=mRetrofitConfig?: RetrofitConfig.DEFAULT

    @Provides
    fun provideRetrofits(context: Context, builder: Retrofit.Builder,client: OkHttpClient, config: RetrofitConfig?,baseUrls:SparseArray<HttpUrl>): SparseArray<Retrofit> {
        //builder....
        builder.client(client)
        config?.applyConfig(context,builder)
        val retrofits = SparseArray<Retrofit>()
        for (i in 0..baseUrls.size()){
            val key = baseUrls.keyAt(i)
            val url = baseUrls.get(key)
            val retrofit = builder.baseUrl(url).build()
            retrofits.put(key,retrofit)
        }
        return retrofits
    }

    @Provides
    fun provideOkHttpClientBuilder()= OkHttpClient.Builder()

    @Provides
    fun provideOkHttpConfig()=mOkHttpConfig?: OkHttpConfig.DEFAULT

    @Provides
    fun provideOkHttpClient(context: Context, builder: OkHttpClient.Builder, config: OkHttpConfig?): OkHttpClient {
        config?.applyConfig(context,builder)
        return builder.build()
    }

    @Provides
    fun provideGsonBuilder()= GsonBuilder()

    @Provides
    fun provideGsonConfig()=mGsonConfig?: GsonConfig.DEFAULT

    @Provides
    fun provideGson(context: Context, builder: GsonBuilder, config: GsonConfig?): Gson {
        config?.applyConfig(context,builder)
        return builder.create()
    }


    interface OkHttpConfig{
        fun applyConfig(context: Context,builder: OkHttpClient.Builder)

        companion object {
            /**
             * default impl
             */
            val DEFAULT = object :OkHttpConfig{
                override fun applyConfig(context: Context, builder: OkHttpClient.Builder) {
                    //todo
                }
            }
        }

    }

    interface GsonConfig{
        fun applyConfig(context: Context,builder: GsonBuilder)
        companion object {
            /**
             * default impl
             */
            val DEFAULT = object :GsonConfig{
                override fun applyConfig(context: Context, builder: GsonBuilder) {
                    //todo
                }
            }
        }
    }

    interface RetrofitConfig{
        fun applyConfig(context: Context,builder: Retrofit.Builder)
        companion object {
            /**
             * default impl
             */
            val DEFAULT = object :RetrofitConfig{
                override fun applyConfig(context: Context, builder: Retrofit.Builder) {
                    //todo
                }
            }
        }
    }

    class Builder(val context: Context){
        internal var retrofitConfig:RetrofitConfig? = null
        internal var okHttpConfig:OkHttpConfig? = null
        internal var gsonConfig:GsonConfig? = null

        fun setRetrofitConfig(config: RetrofitConfig?):Builder{
            retrofitConfig = config
            return this
        }

        fun setOkHttpConfig(config: OkHttpConfig?):Builder{
            okHttpConfig = config
            return this
        }

        fun setGsonConfig(config: GsonConfig?):Builder{
            gsonConfig = config
            return this
        }

        fun build():HttpClientModule{
            return HttpClientModule(this)
        }
    }
}
