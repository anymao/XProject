package com.anymore.example.mvvm.model

import android.app.Application
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.example.mvvm.model.api.WanAndroidHomePageApi
import com.anymore.example.mvvm.model.api.WanAndroidKnowledgeApi
import com.anymore.example.mvvm.model.entry.Banner
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.model.entry.Knowledge
import com.anymore.example.mvvm.model.exception.WanAndroidException
import com.anymore.mvvmkit.mvvm.base.BaseModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * 主界面的业务model
 * Created by liuyuanmao on 2019/3/12.
 */
class MainModel @Inject constructor(application: Application):BaseModel(application) {

    /**
     * 获取首页轮播图
     */
    fun getHomePageBanners():Observable<List<Banner>>{
        return mRepositoryComponent.getRepository()
            .obtainRetrofitService(KEY,WanAndroidHomePageApi::class.java)
            .getBanner()
            .subscribeOn(Schedulers.io())
            .flatMap {
                if (it.errorCode == 0 && it.data != null){
                    Observable.just(it.data)
                }else{
                    Observable.error(WanAndroidException(it.errorMsg?:"获取首页Banner失败!"))
                }
            }
            .observeOn(AndroidSchedulers.mainThread())

    }

    /**
     * 获取首页文章列表
     */
    fun getHomeArticlesList(page:Int):Observable<Pair<Int,List<Article>>>{
        return mRepositoryComponent.getRepository()
            .obtainRetrofitService(KEY,WanAndroidHomePageApi::class.java)
            .getArticles(page)
            .subscribeOn(Schedulers.io())
            .flatMap {
                if (it.errorCode == 0 && it.data != null){
                    Observable.just(Pair(it.data.curPage,it.data.datas))
                }else{
                    Observable.error(WanAndroidException(it.errorMsg?:"获取首页文章失败!"))
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 获取导航页面所有知识体系列表
     */
    fun getAllKnowledges():Observable<List<Knowledge>>{
        return mRepositoryComponent.getRepository()
            .obtainRetrofitService(KEY,WanAndroidKnowledgeApi::class.java)
            .getAllKnowledges()
            .subscribeOn(Schedulers.io())
            .map {
                if (it.errorCode == 0 && it.data != null){
                    return@map it.data!!
                }else{
                    throw WanAndroidException("获取知识体系时出错!")
                }
            }.observeOn(AndroidSchedulers.mainThread())
    }

}