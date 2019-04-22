package com.anymore.example.mvvm.model.paging

import com.anymore.example.mvvm.model.api.WanAndroidHomePageApi
import com.anymore.example.mvvm.model.entry.HomeArticles
import com.anymore.example.mvvm.model.exception.WanAndroidException
import io.reactivex.Observable

/**
 * 使用Wrapper将真实的数据请求细节屏蔽，统一那些仅仅是获取方式不同，而输出结果格式一致的api接口
 * Created by liuyuanmao on 2019/4/22.
 */
interface ArticlesApiWrapper{
    fun loadInitial(page:Int):Observable<HomeArticles>
    fun loadAfter(page: Int):Observable<HomeArticles>
    fun loadBefore(page: Int):Observable<HomeArticles>
}

class HomeArticlesApiWrapper(private val api:WanAndroidHomePageApi) :ArticlesApiWrapper{

    override fun loadInitial(page: Int): Observable<HomeArticles> {
        return api.getArticles(page)
            .map {
                if (it.errorCode == 0){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取首页文章列表失败!")
                }
            }
    }

    override fun loadAfter(page: Int): Observable<HomeArticles> {
        return api.getArticles(page)
            .map {
                if (it.errorCode == 0){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取首页文章列表失败!")
                }
            }
    }

    override fun loadBefore(page: Int): Observable<HomeArticles> {
        return api.getArticles(page)
            .map {
                if (it.errorCode == 0){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取首页文章列表失败!")
                }
            }
    }

}