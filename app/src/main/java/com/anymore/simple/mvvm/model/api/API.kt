package com.anymore.simple.mvvm.model.api

import com.anymore.simple.mvvm.model.entry.Article
import com.anymore.simple.mvvm.model.entry.WanAndroidResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by liuyuanmao on 2019/3/12.
 */
interface WanAndroid{
    @GET("/wxarticle/chapters/json")
    fun getWeixinArticleList():Observable<WanAndroidResponse<List<Article>>>
}