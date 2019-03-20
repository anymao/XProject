package com.anymore.example.mvvm.model.api

import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.model.entry.WanAndroidResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by liuyuanmao on 2019/3/12.
 */
interface WanAndroid{
    /**
     * 获取微信公众号文章列表
     * @return
     */
    @GET("/wxarticle/chapters/json")
    fun getWeixinArticleList():Observable<WanAndroidResponse<List<Article>>>
}