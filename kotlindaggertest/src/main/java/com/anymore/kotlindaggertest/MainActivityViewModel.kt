package com.anymore.kotlindaggertest

import android.app.Application
import android.arch.lifecycle.*
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/2/22.
 */
class MainActivityViewModel @Inject constructor(application: Application):AndroidViewModel(application),LifecycleObserver{
    private var mMessage = MutableLiveData<String>()


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        mMessage.postValue("successful!")
    }


    fun getMessage()=mMessage

}