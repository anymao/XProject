package com.anymore.example.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.anymore.example.mvvm.model.MainModel
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.base.BaseViewModel1
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/3/11.
 */
@ActivityScope
class MainActivityViewModel @Inject constructor(application: Application,model: MainModel):
    BaseViewModel1<MainModel>(application, model) {

    private val mMessage = MutableLiveData<String>()

    fun getMessage()=mMessage
}