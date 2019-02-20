package com.anymore.mvvmkit.mvvm.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Created by liuyuanmao on 2019/2/20.
 */
class ActivityLifecycle:Application.ActivityLifecycleCallbacks {

    private val mActivityWrapperMap:HashMap<Activity,ActivityWrapper> = HashMap()

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        if (activity is IActivity){
            val activityWrapper = ActivityWrapper(activity,activity)
            mActivityWrapperMap[activity] = activityWrapper
        }
    }

    override fun onActivityStarted(activity: Activity?) {
        activity?.let { mActivityWrapperMap[it] }?.onStart()
    }

    override fun onActivityResumed(activity: Activity?) {
        activity?.let { mActivityWrapperMap[it] }?.onResume()
    }

    override fun onActivityPaused(activity: Activity?) {
        activity?.let { mActivityWrapperMap[it] }?.onPause()
    }

    override fun onActivityStopped(activity: Activity?) {
        activity?.let { mActivityWrapperMap[it] }?.onStop()
    }

    override fun onActivityDestroyed(activity: Activity?) {
       activity?.let { mActivityWrapperMap[it] }?.let{
            it.onDestroy()
       mActivityWrapperMap.remove(activity)
       }

    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        activity?.let { mActivityWrapperMap[it] }?.onSaveInstanceState(outState)
    }

}