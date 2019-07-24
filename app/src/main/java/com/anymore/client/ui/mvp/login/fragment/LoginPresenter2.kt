package com.anymore.client.ui.mvp.login.fragment

import android.app.Application
import com.anymore.client.ui.mvp.login.LoginContract
import com.anymore.client.utils.withLoading
import com.anymore.mvpkit.di.scope.FragmentScope
import com.anymore.mvpkit.mvp.base.BasePresenter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/7/24.
 */
@FragmentScope
class LoginPresenter2 @Inject constructor(application: Application, view:LoginContract.IView, private val mModel:LoginContract.IModel) : BasePresenter<LoginContract.IView>(application,view),LoginContract.IPresenter{

    override fun login(username: String, password: String) {

        val disposable = mModel.login(username,password)
            .withLoading(mView,"请稍后...")
            .subscribeBy(
                onNext = {
                    if (it){
                        mView.loginSuccess()
                    }else{
                        mView.showError("登陆失败，傻了吧？")
                    }
                },
                onError = {
                    mView.showError(it.message?:"")
                }
            )
        addDisposable(disposable)
    }

}