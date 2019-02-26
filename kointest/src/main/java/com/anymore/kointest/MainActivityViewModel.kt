package com.anymore.kointest

import android.arch.lifecycle.*
import android.util.Log
import com.anymore.kointest.di.HelloRepository

/**
 * Created by liuyuanmao on 2019/2/25.
 */
class MainActivityViewModel(private val repository: HelloRepository):ViewModel(),LifecycleObserver {
    val mHello = MutableLiveData<String>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        Log.d("MainActivityViewModel",repository?.toString())
        mHello.postValue(repository.sayHello())
    }
}