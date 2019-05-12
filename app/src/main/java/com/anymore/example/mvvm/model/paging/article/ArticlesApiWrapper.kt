package com.anymore.example.mvvm.model.paging.article

import com.anymore.example.mvvm.model.api.WanAndroidCollectApi
import com.anymore.example.mvvm.model.api.WanAndroidHomePageApi
import com.anymore.example.mvvm.model.api.WanAndroidKnowledgeApi
import com.anymore.example.mvvm.model.entry.Articles
import com.anymore.example.mvvm.model.entry.ResponseCode
import com.anymore.example.mvvm.model.exception.WanAndroidException
import io.reactivex.Observable

/**
 * 使用Wrapper将真实的数据请求细节屏蔽，统一那些仅仅是获取方式不同，而输出结果格式一致的api接口
 * Created by liuyuanmao on 2019/4/22.
 */
interface ArticlesApiWrapper{
    fun loadInitial(page: Int):Observable<Articles>
    fun loadAfter(page: Int):Observable<Articles>
    fun loadBefore(page: Int):Observable<Articles>
}

class HomeArticlesApiWrapper(private val api:WanAndroidHomePageApi) :
    ArticlesApiWrapper {

    override fun loadInitial(page: Int): Observable<Articles> {
        return api.getArticles(page)
            .map {
                if (it.errorCode == ResponseCode.OK){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取首页文章列表失败!")
                }
            }
    }

    override fun loadAfter(page: Int): Observable<Articles> {
        return api.getArticles(page)
            .map {
                if (it.errorCode == ResponseCode.OK){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取首页文章列表失败!")
                }
            }
    }

    override fun loadBefore(page: Int): Observable<Articles> {
        return api.getArticles(page)
            .map {
                if (it.errorCode == ResponseCode.OK){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取首页文章列表失败!")
                }
            }
    }

}

class KnowledgesArtclesApiWrapper(private val api: WanAndroidKnowledgeApi,private val cid:Int):
    ArticlesApiWrapper {

    override fun loadInitial(page: Int): Observable<Articles> {
        return api.getSubKnowledges(page,cid)
            .map {
                if (it.errorCode == ResponseCode.OK){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取${cid}下知识体系文章列表失败!")
                }
            }
    }

    override fun loadAfter(page: Int): Observable<Articles> {
        return api.getSubKnowledges(page,cid)
            .map {
                if (it.errorCode == ResponseCode.OK){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取${cid}下知识体系文章列表失败!")
                }
            }
    }

    override fun loadBefore(page: Int): Observable<Articles> {
        return api.getSubKnowledges(page,cid)
            .map {
                if (it.errorCode == ResponseCode.OK){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取${cid}下知识体系文章列表失败!")
                }
            }
    }
}

class CollectedArticlesApiWrapper(private val api:WanAndroidCollectApi):
    ArticlesApiWrapper {

    override fun loadInitial(page: Int): Observable<Articles> {
        return api.getAllCollectedArticles(page)
            .map {
                if (it.errorCode == ResponseCode.OK){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取收藏列表失败!")
                }
            }
    }

    override fun loadAfter(page: Int): Observable<Articles> {
        return api.getAllCollectedArticles(page)
            .map {
                if (it.errorCode == ResponseCode.OK){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取收藏列表失败!")
                }
            }
    }

    override fun loadBefore(page: Int): Observable<Articles> {
        return api.getAllCollectedArticles(page)
            .map {
                if (it.errorCode == ResponseCode.OK){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取收藏列表失败!")
                }
            }
    }
}