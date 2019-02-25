package com.anymore.kointest

import android.arch.lifecycle.*
import com.anymore.kointest.di.HelloRepository

/**
 * Created by liuyuanmao on 2019/2/25.
 */
class MainActivityViewModel(private val repository: HelloRepository):ViewModel(),LifecycleObserver {
    val mHello = MutableLiveData<String>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        mHello.postValue(repository.sayHello())
    }
}