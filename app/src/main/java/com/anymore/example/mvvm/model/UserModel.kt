package com.anymore.example.mvvm.model

import android.app.Application
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.example.mvvm.model.api.WanAndroidUserApi
import com.anymore.example.mvvm.model.entry.UserInfo
import com.anymore.example.mvvm.model.entry.WanAndroidResponse
import com.anymore.mvvmkit.mvvm.base.BaseModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * 用户管理model层，与用户信息有关的操作在这里
 * Created by liuyuanmao on 2019/3/28.
 */
class UserModel @Inject constructor(application: Application):BaseModel(application){

    fun register(username:String,pwd:String,rePwd:String):Observable<WanAndroidResponse<UserInfo>> = mRepositoryComponent
        .getRepository()
        .obtainRetrofitService(KEY,WanAndroidUserApi::class.java)
        .register(username,pwd,rePwd)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun login(username: String,pwd: String):Observable<WanAndroidResponse<UserInfo>> = mRepositoryComponent
        .getRepository()
        .obtainRetrofitService(KEY,WanAndroidUserApi::class.java)
        .login(username,pwd)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}