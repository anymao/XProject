package com.anymore.example.mvvm.model

import android.app.Application
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.example.mvvm.model.api.WanAndroidUserApi
import com.anymore.mvvmkit.mvvm.base.BaseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/3/28.
 */
class RegisterModel @Inject constructor(application: Application):BaseModel(application){

    fun register(username:String,pwd:String,rePwd:String){
        mRepositoryComponent.getRepository().obtainRetrofitService(KEY,WanAndroidUserApi::class.java)
            .register(username,pwd,rePwd)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}