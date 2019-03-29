package com.anymore.example.mvvm.viewmodel

import android.app.Application
import android.text.TextUtils
import com.anymore.example.mvvm.model.UserModel
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.SingleLiveEvent
import com.anymore.mvvmkit.mvvm.base.BaseViewModel1
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/3/29.
 */
@ActivityScope
class LoginViewModel @Inject constructor(application: Application,userModel: UserModel)
    : BaseViewModel1<UserModel>(application,userModel) {

    val mErrorMessage = SingleLiveEvent<String>()
    val mMessage = SingleLiveEvent<String>()

    fun login(username: String?, pwd: String?) {
        if (checkUser(username, pwd)) {
            val disposable = mModel.login(username!!,pwd!!)
                .subscribeBy(onNext = {
                    if (it.errorCode == 0){
                        mMessage.value = "注册成功!"
                    }else{
                        mErrorMessage.value = it.errorMsg
                    }
                },onError = {
                    mErrorMessage.value = it.message
                })
            addToCompositeDisposable(disposable)
        }
    }

    private fun checkUser(username: String?, pwd: String?): Boolean {
        if (TextUtils.isEmpty(username)){
            mErrorMessage.value = "用户名不能为空!"
            return false
        }
        if (TextUtils.isEmpty(pwd)){
            mErrorMessage.value = "密码不能为空!"
            return false
        }
        return true
    }
}