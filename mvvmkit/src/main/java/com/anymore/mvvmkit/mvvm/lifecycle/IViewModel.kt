package com.anymore.mvvmkit.mvvm.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import timber.log.Timber

/**
 * Created by liuyuanmao on 2019/2/20.
 */
interface IViewModel:LifecycleObserver{
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Timber.v("onCreate")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        Timber.v("onStart")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        Timber.v("onResume")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        Timber.v("onPause")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        Timber.v("onStop")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        Timber.v("onDestroy")
    }
}