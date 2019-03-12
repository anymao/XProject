package com.anymore.simple.mvvm

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by liuyuanmao on 2019/3/12.
 */
interface WanAndroid{
    @GET("/wxarticle/chapters/json")
    fun getWeixinArticleList():Observable<WanAndroidResponse<List<Article>>>
}