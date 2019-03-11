package com.anymore.simple.mvvm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.base.BaseModel
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/3/11.
 */
@ActivityScope
class MainActivityViewModel @Inject constructor(application: Application) :BaseViewModel<BaseModel>(application) {
    private val mMessage = MutableLiveData<String>()

    override fun onStart() {
        super.onStart()
        mMessage.postValue("hello")
    }

    fun getMessage()=mMessage
}