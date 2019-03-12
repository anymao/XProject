package com.anymore.mvvmkit.mvvm.base

import android.app.Application
import com.anymore.mvvmkit.di.component.RepositoryComponent
import com.anymore.mvvmkit.getRepositoryComponent

/**
 * Created by liuyuanmao on 2019/2/23.
 */
open class BaseModel(mApplication: Application): IModel{
    protected val mRepositoryComponent:RepositoryComponent = mApplication.getRepositoryComponent()
}
