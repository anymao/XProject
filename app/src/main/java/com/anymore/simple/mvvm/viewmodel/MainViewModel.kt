package com.anymore.simple.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.anymore.mvvmkit.mvvm.BaseViewModel
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/2/21.
 */
class MainViewModel @Inject
constructor(application: Application)
    :BaseViewModel(application){
    private val mMessage = MutableLiveData<String>()
    override fun onStart() {
        super.onStart()
        mMessage.value = "onStart"
    }

    fun getMessage() = mMessage
}