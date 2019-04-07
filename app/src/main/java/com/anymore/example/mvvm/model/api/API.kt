package com.anymore.example.mvvm.model.api

import com.anymore.example.mvvm.model.entry.*
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by liuyuanmao on 2019/3/12.
 */
interface WanAndroid{
    /**
     * 获取微信公众号文章列表
     * @return
     */
    @GET("/wxarticle/chapters/json")
    fun getWeixinArticleList():Observable<WanAndroidResponse<List<WxArticle>>>
}

/**
 * 用户接口
 */
interface WanAndroidUserApi{

    /**
     * 注册接口
     */
    @FormUrlEncoded
    @POST("/user/register")
    fun register(@Field("username") username:String,@Field("password") password:String,@Field("repassword") repassword:String):Observable<WanAndroidResponse<UserInfo>>

    /**
     * 登陆
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("username") username:String,@Field("password") password:String):Observable<WanAndroidResponse<UserInfo>>

    /**
     * 注销
     */
    @GET("/logout/json")
    fun logout():Observable<WanAndroidResponse<String>>
}

/**
 * 首页数据接口
 */
interface WanAndroidHomePageApi{

    @GET("/banner/json")
    fun getBanner():Observable<WanAndroidResponse<List<Banner>>>

    @GET("/article/list/{page}/json")
    fun getArticles(@Path("page") page:Int):Observable<WanAndroidResponse<HomeArticles>>
}