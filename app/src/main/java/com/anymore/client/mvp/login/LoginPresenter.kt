package com.anymore.client.mvp.login

import android.app.Application
import com.anymore.client.utils.withLoading
import com.anymore.mvpkit.di.scope.ActivityScope
import com.anymore.mvpkit.mvp.base.BasePresenter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/7/23.
 */
@ActivityScope
class LoginPresenter @Inject constructor(
    application: Application,
    mView: LoginContract.IView,
    private val mModel: LoginContract.IModel
) :
    BasePresenter<LoginContract.IView>(application, mView), LoginContract.IPresenter {

    override fun login(username: String, password: String) {
        val disposable = mModel.login(username, password)
            .withLoading(mView, "正在登陆...")
            .subscribeBy(
                onNext = {
                    if (it) {
                        mView.loginSuccess()
                    } else {
                        mView.showError("账号或者密码错误")
                    }
                },
                onError = {
                    mView.showError(it.message ?: "")
                }
            )
        addDisposable(disposable)
    }

}