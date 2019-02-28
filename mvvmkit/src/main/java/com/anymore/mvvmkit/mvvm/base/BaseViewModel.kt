package com.anymore.mvvmkit.mvvm.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by liuyuanmao on 2019/2/20.
 */
open class BaseViewModel<M: BaseModel>:AndroidViewModel, IViewModel {

    protected lateinit var mModel:M

    private val mCompositeDisposable:CompositeDisposable by lazy { CompositeDisposable() }

    constructor(application: Application):super(application)

    constructor(application: Application,model: M):super(application){
        mModel = model
    }

    override fun onDestroy() {
        mCompositeDisposable.clear()
        super.onDestroy()
    }

    protected fun addToCompositeDisposable(disposable: Disposable){
        mCompositeDisposable.add(disposable)
    }
}
