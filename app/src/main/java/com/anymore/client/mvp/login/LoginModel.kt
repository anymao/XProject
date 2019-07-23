package com.anymore.client.mvp.login

import android.app.Application
import android.text.TextUtils
import com.anymore.mvpkit.mvp.base.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/7/23.
 */
class LoginModel @Inject constructor(private val application: Application): BaseModel(application),LoginContract.IModel{
    override fun login(username: String, password: String): Observable<Boolean> {
        return Observable.create {
            if (username.isNullOrEmpty()){
                it.onError(Throwable("用户名不能为空!"))
                return@create
            }
            if (password.isNullOrEmpty()){
                it.onError(Throwable("密码不能为空!"))
                return@create
            }
            Thread.sleep(5000)
            it.onNext(TextUtils.equals(username,"anymore") && TextUtils.equals(password,"12345"))
            it.onComplete()
        }
    }
}