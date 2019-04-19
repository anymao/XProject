package com.anymore.example.mvvm.model.paging

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.example.mvvm.model.api.WanAndroidHomePageApi
import com.anymore.example.mvvm.model.entry.HomeArticle
import com.anymore.mvvmkit.getRepositoryComponent


/**
 * Created by liuyuanmao on 2019/4/19.
 */
class HomeArticlesSourceFactory(private val application: Application):DataSource.Factory<Int,HomeArticle>(){
    val source = MutableLiveData<HomeArticlesSource>()
    override fun create(): DataSource<Int, HomeArticle> {
        val api = application.getRepositoryComponent()
            .getRepository()
            .obtainRetrofitService(KEY,WanAndroidHomePageApi::class.java)
        val s = HomeArticlesSource(api)
        source.postValue(s)
        return s
    }
}