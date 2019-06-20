package com.anymore.example.mvvm.model.paging.article

import android.app.Application
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.example.mvvm.model.api.WanAndroidCollectApi
import com.anymore.example.mvvm.model.api.WanAndroidHomePageApi
import com.anymore.example.mvvm.model.api.WanAndroidKnowledgeApi
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.model.paging.Listing
import com.anymore.mvvmkit.getRepositoryComponent


/**
 * Created by liuyuanmao on 2019/4/19.
 */
class ArticlesRepository(private val application: Application){

    /**
     *主页文章列表
     */
    fun getHomeArticlesListing(): Listing<Article> {
        val api = application.getRepositoryComponent().getRepository().obtainRetrofitService(KEY,WanAndroidHomePageApi::class.java)
        val apiWrapper = HomeArticlesApiWrapper(api)
        val factory = ArticlesSourceFactory(apiWrapper)
        val data = LivePagedListBuilder<Int,Article>(factory,
            PagedList.Config
                .Builder()
                .setPageSize(20)
                .setPrefetchDistance(20)
                .build())
            .build()

        return Listing<Article>(
            pagedList = data,
            status = Transformations.switchMap(factory.source) {
                it.mStatus
            },
            retry = Transformations.switchMap(factory.source){
                it.mRetry
            }
        )
    }

    /**
     * 获取某个知识体系下的文章列表
     */
    fun getKnowledgesArticlesListing(cid: Int): Listing<Article> {
        val api = application.getRepositoryComponent().getRepository().obtainRetrofitService(KEY,WanAndroidKnowledgeApi::class.java)
        val apiWrapper = KnowledgesArticlesApiWrapper(api, cid)
        val factory = ArticlesSourceFactory(apiWrapper)
        val data = LivePagedListBuilder<Int,Article>(factory,
            PagedList.Config
                .Builder()
                .setPageSize(20)
                .setPrefetchDistance(20)
                .build())
            .build()

        return Listing<Article>(
            pagedList = data,
            status = Transformations.switchMap(factory.source) {
                it.mStatus
            },
            retry = Transformations.switchMap(factory.source){
                it.mRetry
            }
        )
    }

    /**
     * 获取已收藏文章列表
     */
    fun getCollectedArticlesListing(): Listing<Article> {
        val api = application.getRepositoryComponent().getRepository().obtainRetrofitService(KEY,WanAndroidCollectApi::class.java)
        val apiWrapper = CollectedArticlesApiWrapper(api)
        val factory = ArticlesSourceFactory(apiWrapper)
        val data = LivePagedListBuilder<Int,Article>(factory,
            PagedList.Config
                .Builder()
                .setPageSize(20)
                .setPrefetchDistance(20)
                .build())
            .build()

        return Listing<Article>(
            pagedList = data,
            status = Transformations.switchMap(factory.source) {
                it.mStatus
            },
            retry = Transformations.switchMap(factory.source){
                it.mRetry
            }
        )
    }
}

