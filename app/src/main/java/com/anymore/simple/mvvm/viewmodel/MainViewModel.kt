package com.anymore.simple.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.BaseModel
import com.anymore.mvvmkit.mvvm.BaseViewModel
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/2/21.
 */
@ActivityScope
class MainViewModel @Inject
constructor(application: Application)
    :BaseViewModel<BaseModel>(application){
    private val mMessage = MutableLiveData<String>()
    override fun onStart() {
        super.onStart()
        mMessage.value = "onStart"
    }

    fun getMessage() = mMessage
}