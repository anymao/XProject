package com.anymore.client.ui.mvp.login

import com.anymore.mvpkit.mvp.base.BaseContract
import io.reactivex.Observable

/**
 * Created by liuyuanmao on 2019/7/23.
 */
interface LoginContract {

    interface IView: BaseContract.IBaseView{
        fun loginSuccess()
    }

    interface IPresenter: BaseContract.IBasePresenter{
        fun login(username:String,password:String)
    }

    interface IModel: BaseContract.IBaseModel{
        fun login(username:String,password:String):Observable<Boolean>
    }
}