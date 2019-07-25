package com.anymore.client.ui.mvp.login

import android.app.Application
import com.anymore.client.repository.remote.KEY
import com.anymore.client.repository.remote.WanAndroidUserApi
import com.anymore.mvpkit.exts.getRepositoryComponent
import com.anymore.mvpkit.mvp.base.BaseModel
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/7/23.
 */
class LoginModel @Inject constructor(application: Application): BaseModel(application),LoginContract.IModel{
    override fun login(username: String, password: String): Observable<Boolean> {
        val gson = mApplication.getRepositoryComponent().gson()
        val okHttpClient = mApplication.getRepositoryComponent().okHttpClient()
        Timber.d(gson.toString())
        Timber.d(okHttpClient.toString())
        return mRepositoryComponent.getRepository().obtainRetrofitService(KEY,WanAndroidUserApi::class.java)
            .login(username,password)
            .map {
                it.errorCode == 0
            }
    }
}