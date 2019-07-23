package com.anymore.client.repository.remote

import com.anymore.client.repository.bean.UserInfo
import com.anymore.client.repository.bean.WanAndroidResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by liuyuanmao on 2019/7/23.
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