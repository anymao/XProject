package com.anymore.client.ui.mvp.login

import android.app.Application
import com.anymore.client.utils.withLoading
import com.anymore.mvpkit.di.scope.ActivityScope
import com.anymore.mvpkit.exts.getRepositoryComponent
import com.anymore.mvpkit.mvp.base.BasePresenter
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/7/23.
 */
@ActivityScope
class LoginPresenter @Inject constructor(
    application: Application,
    view: LoginContract.IView,
    private val mModel: LoginContract.IModel
) :
    BasePresenter<LoginContract.IView>(application, view), LoginContract.IPresenter {

    override fun login(username: String, password: String) {
        val gson = application.getRepositoryComponent().gson()
        val okHttpClient = application.getRepositoryComponent().okHttpClient()
        Timber.d(gson.toString())
        Timber.d(okHttpClient.toString())
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