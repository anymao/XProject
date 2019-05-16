package com.anymore.example.mvvm.model.paging.article

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.anymore.example.mvvm.model.entry.Article


/**
 * Created by liuyuanmao on 2019/4/19.
 */
class ArticlesSourceFactory(private val apiWrapper: ArticlesApiWrapper):DataSource.Factory<Int,Article>(){
    val source = MutableLiveData<ArticlesSource>()
    override fun create(): DataSource<Int, Article> {
        val s = ArticlesSource(apiWrapper)
        source.postValue(s)
        return s
    }
}