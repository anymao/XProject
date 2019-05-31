package com.anymore.example.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.anymore.example.mvvm.model.UserModel
import com.anymore.example.mvvm.model.db.entry.UserInfo
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.SingleLiveEvent
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by anymore on 2019/4/20.
 */
@ActivityScope
class SplashActivityViewModel @Inject constructor(application: Application, private val mModel: UserModel) :
    BaseViewModel(application) {
    val mUserInfo = MutableLiveData<UserInfo>()
    val mHasLoginedUser = SingleLiveEvent<Boolean>()
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        val disposable = mModel.getCurrentUser()
            .subscribeBy(
                onSuccess = {
                    mUserInfo.postValue(it)
                    mHasLoginedUser.postValue(true)
                },
                onError = { mHasLoginedUser.postValue(false) }
            )
        addToCompositeDisposable(disposable)
    }
}