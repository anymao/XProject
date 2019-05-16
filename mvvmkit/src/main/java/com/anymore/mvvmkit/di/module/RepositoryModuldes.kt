package com.anymore.mvvmkit.di.module

import android.app.Application
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.util.SparseArray
import com.anymore.mvvmkit.BuildConfig
import com.anymore.mvvmkit.repository.IRepositoryManager
import com.anymore.mvvmkit.repository.RepositoryManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

/**
 * Created by liuyuanmao on 2019/3/7.
 */
@Module
class RepositoryModule{

    @Singleton
    @Provides
    fun provideIRepositoryManager(application: Application,
                                  retrofits:Lazy<SparseArray<Retrofit>>,
                                  roomDatabaseConfig: RepositoryConfigsModule.RoomDatabaseConfig):IRepositoryManager{
        return RepositoryManager(application,retrofits,roomDatabaseConfig)
    }
}

/**
 * 仓库配置module，包含本地数据库配置和网络仓库配置
 */
@Module
class RepositoryConfigsModule private constructor(builder: Builder) {
    private var mContext:Context
    //网络配置
    private var mUrls:SparseArray<HttpUrl>
    private var mRetrofitConfig:RetrofitConfig? = null
    private var mOkHttpConfig:OkHttpConfig? = null
    private var mGsonConfig:GsonConfig? = null
    //数据库配置
    private var mRoomDatabaseConfig:RoomDatabaseConfig?=null

    init {
        mContext = builder.context
        mUrls = builder.getUrls()
        mRetrofitConfig = builder.retrofitConfig
        mOkHttpConfig = builder.okHttpConfig
        mGsonConfig = builder.gsonConfig
        mRoomDatabaseConfig = builder.roomDatabaseConfig
    }

    @Provides
    fun provideContext()=mContext

    @Provides
    fun provideApplication():Application=mContext.applicationContext as Application

    @Provides
    fun provideHttpUrls()=mUrls

    @Provides
    fun provideRetrofitConfig()=mRetrofitConfig?: RetrofitConfig.DEFAULT

    @Provides
    fun provideOkHttpConfig()=mOkHttpConfig?: OkHttpConfig.DEFAULT

    @Provides
    fun provideGsonConfig()=mGsonConfig?: GsonConfig.DEFAULT

    @Provides
    fun provideRoomDatabaseConfig()=mRoomDatabaseConfig?: RoomDatabaseConfig.DEFAULT

    companion object {
        fun builder(context: Context) = Builder(context)
    }

    class Builder(val context: Context){
        private val _urls:SparseArray<HttpUrl> by lazy { SparseArray<HttpUrl>() }
        var retrofitConfig: RetrofitConfig? = null
        var okHttpConfig: OkHttpConfig? = null
        var gsonConfig: GsonConfig? = null
        var roomDatabaseConfig: RoomDatabaseConfig? = null

        fun getUrls()=_urls

        /**
         * @param config Retrofit 的配置，如果为空将使用默认配置
         */
        fun setRetrofitConfig(@Nullable config:RetrofitConfig?):Builder{
            retrofitConfig = config
            return this
        }

        fun setOkHttpConfig(@Nullable config: OkHttpConfig?):Builder{
            okHttpConfig = config
            return this
        }

        fun setGsonConfig(@Nullable config: GsonConfig?):Builder{
            gsonConfig = config
            return this
        }

        fun setRoomDatabaseConfig(@Nullable config:RoomDatabaseConfig?):Builder{
            roomDatabaseConfig = config
            return this
        }

        fun addUrl(@NonNull key:Int,@NonNull url:HttpUrl){
            _urls.put(key,url)
        }

        fun addUrl(@NonNull key: Int,@NonNull url:String){
            HttpUrl.parse(url)?.let { addUrl(key,it) }?:run { throw RuntimeException("your url $url parse to HttpUrl failed!!!") }
        }

        fun build():RepositoryConfigsModule{
            return RepositoryConfigsModule(this)
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
                    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                }
            }
        }
    }

    interface OkHttpConfig{
        fun applyConfig(context: Context,builder: OkHttpClient.Builder)

        companion object {
            /**
             * default impl
             */
            val DEFAULT = object :OkHttpConfig{
                override fun applyConfig(context: Context, builder: OkHttpClient.Builder) {
                    val okLogger = HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG){HttpLoggingInterceptor.Level.BODY}else{HttpLoggingInterceptor.Level.NONE}
                    }
                    builder.addInterceptor(okLogger)
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

    interface RoomDatabaseConfig{

        fun config(context: Context, builder: RoomDatabase.Builder<*>)

        companion object {
            /**
             * default impl
             */
            val DEFAULT = object :RoomDatabaseConfig{
                override fun config(context: Context, builder: RoomDatabase.Builder<*>) {

                }
            }
        }
    }

    interface RepositoryConfig{
        fun applyConfig(context: Context,builder: Builder)
    }
}



//////////////////***************************////////////////////
//////////////////    Remote repository      ////////////////////
//////////////////***************************////////////////////

@Module
class HttpClientModule{

    @Provides
    fun provideRetrofitBuilder()= Retrofit.Builder()

    @Provides
    fun provideRetrofits(context: Context, builder: Retrofit.Builder, client: OkHttpClient, config: RepositoryConfigsModule.RetrofitConfig?, baseUrls:SparseArray<HttpUrl>): SparseArray<Retrofit> {
        //builder....
        Timber.d(baseUrls.toString())
        builder.client(client)
        config?.applyConfig(context,builder)
        val retrofits = SparseArray<Retrofit>()
        for (i in 0 until baseUrls.size()){
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
    fun provideOkHttpClient(context: Context, builder: OkHttpClient.Builder, config: RepositoryConfigsModule.OkHttpConfig?): OkHttpClient {
        config?.applyConfig(context,builder)
        return builder.build()
    }

    @Provides
    fun provideGsonBuilder()= GsonBuilder()

    @Provides
    fun provideGson(context: Context, builder: GsonBuilder, config: RepositoryConfigsModule.GsonConfig?): Gson {
        config?.applyConfig(context,builder)
        return builder.create()
    }
}