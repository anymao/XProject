package com.anymore.example.mvvm.model.paging

import android.app.Application
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import com.anymore.example.mvvm.model.entry.HomeArticle

/**
 * Created by liuyuanmao on 2019/4/19.
 */
class HomeArticlesRepository(application: Application){
    private val factory:HomeArticlesSourceFactory = HomeArticlesSourceFactory(application)

    fun getListing():Listing<HomeArticle>{
        val data = LivePagedListBuilder<Int,HomeArticle>(factory,2)
            .build()
        return Listing<HomeArticle>(
            pagedList = data,
            status = Transformations.switchMap(factory.source) {
                it.mStatus
            },
            retry = {
                factory.source.value?.retry()
            }
        )
    }
}

