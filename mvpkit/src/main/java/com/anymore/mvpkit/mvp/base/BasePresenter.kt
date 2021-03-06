package com.anymore.mvpkit.mvp.base

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.support.annotation.StringRes
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by liuyuanmao on 2019/7/16.
 */
abstract class BasePresenter<V:BaseContract.IBaseView>(protected val application: Application,protected val mView:V):BaseContract.IBasePresenter{

    protected val mCompositeDisposable by lazy { CompositeDisposable() }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    override fun onCreate() {
        super.onCreate()
    }

    protected fun addDisposable(disposable: Disposable){
        mCompositeDisposable.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }

    protected fun getString(@StringRes stringId:Int)=application.getString(stringId)
}