package com.anymore.example.mvvm.viewmodel

import android.app.Application
import android.text.TextUtils
import com.anymore.example.mvvm.model.UserModel
import com.anymore.example.mvvm.model.entry.ResponseCode
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.SingleLiveEvent
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/3/28.
 */
@ActivityScope
class RegisterActivityViewModel @Inject constructor(application: Application, val mModel: UserModel) :
    BaseViewModel(application) {
    val mErrorMessage = SingleLiveEvent<String>()
    val mMessage = SingleLiveEvent<String>()
    fun register(username: String?, pwd: String?, rePwd: String?) {
        if (checkUser(username, pwd, rePwd)) {
            val disposable = mModel.register(username!!, pwd!!, rePwd!!)
                .subscribeBy(onNext = {
                    if (it.errorCode == ResponseCode.OK) {
                        mMessage.value = "注册成功!"
                    } else {
                        mErrorMessage.value = it.errorMsg
                    }
                }, onError = {
                    mErrorMessage.value = it.message
                })
            addToCompositeDisposable(disposable)
        }
    }

    private fun checkUser(username: String?, pwd: String?, rePwd: String?): Boolean {
        if (TextUtils.isEmpty(username)) {
            mErrorMessage.value = "用户名不能为空!"
            return false
        }
        if (TextUtils.isEmpty(pwd)) {
            mErrorMessage.value = "密码不能为空!"
            return false
        }
        if (TextUtils.isEmpty(rePwd)) {
            mErrorMessage.value = "确认密码不能为空!"
            return false
        }
        if (!TextUtils.equals(pwd, rePwd)) {
            mErrorMessage.value = "两次密码不一致!"
            return false
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        mModel.onClear()
    }
}


