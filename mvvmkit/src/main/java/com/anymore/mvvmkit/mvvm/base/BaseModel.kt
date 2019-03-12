package com.anymore.mvvmkit.mvvm.base

import android.app.Application
import com.anymore.mvvmkit.di.component.RepositoryComponent
import com.anymore.mvvmkit.getRepositoryComponent

/**
 * Created by liuyuanmao on 2019/2/23.
 */
open class BaseModel(protected val mApplication: Application): IModel{
    protected lateinit var mRepositoryComponent:RepositoryComponent
    init {
        mApplication.getRepositoryComponent()
    }
}
