package com.anymore.example.mvvm.model.api

import com.anymore.example.mvvm.model.db.entry.UserInfo
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
    fun getArticles(@Path("page") page:Int):Observable<WanAndroidResponse<Articles>>
}

interface WanAndroidKnowledgeApi{

    //获取所有知识体系
    @GET("/tree/json")
    fun getAllKnowledges():Observable<WanAndroidResponse<List<Knowledge>>>

    //获取某个知识体系下的所有文章
    @GET("/article/list/{page}/json")
    fun getSubKnowledges(@Path("page") page:Int,@Query("cid") cid:Int):Observable<WanAndroidResponse<Articles>>
}

/**
 * 收藏相关接口
 */
interface WanAndroidCollectApi{

    //获取所有收藏的文章
    @GET("/lg/collect/list/{page}/json")
    fun getAllCollectedArticles(@Path("page") page: Int):Observable<WanAndroidResponse<Articles>>

    //收藏站内指定id文章
    @POST("/lg/collect/{id}/json")
    fun collectWanAndroidArticle(@Path("id") id:Int):Observable<WanAndroidResponse<*>>

    //收藏站外文章
    @POST("/lg/collect/add/json")
    fun collectOtherArticle(@Field("title") title:String, @Field("author") author:String, @Field("link") link:String):Observable<WanAndroidResponse<*>>

    //取消收藏方式1
    @POST("/lg/uncollect_originId/{id}/json")
    fun uncollectWanAndroidArticle(@Path("id") id: Int):Observable<WanAndroidResponse<*>>

    //取消收藏方式2
    @POST("/lg/uncollect/{id}/json")
    fun uncollectArticle(@Path("id") id: Int,@Field("originId") originId:Int=-1):Observable<WanAndroidResponse<*>>

}
