package com.anymore.mvvmkit.mvvm.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.support.annotation.StringRes
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

/**
 * 没有关联Model层的简单ViewModel
 * Created by liuyuanmao on 2019/2/20.
 */
open class BaseViewModel(application: Application):AndroidViewModel(application), IViewModel {

    private val mCompositeDisposable:CompositeDisposable by lazy { CompositeDisposable() }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        Timber.d("onDestroy")
        mCompositeDisposable.clear()
    }

    protected fun addToCompositeDisposable(disposable: Disposable){
        mCompositeDisposable.add(disposable)
    }

    protected fun getString(@StringRes resId:Int):String{
        return getApplication<Application>().getString(resId)
    }
}
