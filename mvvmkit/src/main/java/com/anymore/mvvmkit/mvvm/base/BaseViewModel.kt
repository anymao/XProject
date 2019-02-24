package com.anymore.mvvmkit.mvvm.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

/**
 * Created by liuyuanmao on 2019/2/20.
 */
open class BaseViewModel<M: BaseModel>:AndroidViewModel, IViewModel {
    protected lateinit var mModel:M

    constructor(application: Application):super(application)

    constructor(application: Application,model: M):super(application){
        mModel = model
    }
}
