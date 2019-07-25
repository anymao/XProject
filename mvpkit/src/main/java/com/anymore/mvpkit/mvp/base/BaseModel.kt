package com.anymore.mvpkit.mvp.base

import android.app.Application
import android.support.annotation.StringRes
import com.anymore.mvpkit.di.component.RepositoryComponent
import com.anymore.mvpkit.exts.getRepositoryComponent

/**
 * M层基类，封装了取全局仓储层对象的操作(通过[BaseModel.mRepositoryComponent]获取仓储层对象管理器)
 * Created by liuyuanmao on 2019/7/16.
 */
open class BaseModel(protected val mApplication: Application): BaseContract.IBaseModel{

    protected val mRepositoryComponent: RepositoryComponent = mApplication.getRepositoryComponent()

    protected fun getString(@StringRes stringId:Int):String=mApplication.getString(stringId)
}