package com.anymore.mvvmkit.mvvm.lifecycle

import android.app.Activity
import android.text.TextUtils
import timber.log.Timber
import java.util.*

/**
 * activity 管理栈
 */
class ActivityStackManager private constructor(){

    private val mActivityStack by lazy { LinkedList<Activity>() }

    companion object {
        val instance by lazy { ActivityStackManager() }
    }

    internal fun add(activity: Activity){
        Timber.d("add activity:$activity")
        mActivityStack.add(activity)
    }

    internal fun remove(activity: Activity){
        Timber.d("remove activity:$activity")
        mActivityStack.remove(activity)
    }

    fun getTopActivity():Activity?{
        if (mActivityStack.isNotEmpty()){
            return mActivityStack.last!!
        }
        return null
    }

    fun finishActivity(activity: Activity){
        if (!activity.isFinishing){
            activity.finish()
        }
    }

    fun finishActivity(activityName:String){
        for (activity in mActivityStack){
            if (TextUtils.equals(activityName,activity.javaClass.name)){
                finishActivity(activity)
            }
        }
    }

    fun finishUntil(activity: Activity){
        val  iterator = mActivityStack.iterator()
        while (iterator.hasNext()){
            val nextActivity = iterator.next()
            if (nextActivity != activity){
                finishActivity(nextActivity)
            }else{
                break
            }
        }
    }

    fun finishUntil(activityName: String){
        val  iterator = mActivityStack.iterator()
        while (iterator.hasNext()){
            val nextActivity = iterator.next()
            if (!TextUtils.equals(nextActivity.javaClass.name,activityName)){
                finishActivity(nextActivity)
            }else{
                break
            }
        }
    }

    fun finishAll(){
        for (activity in mActivityStack){
            finishActivity(activity)
        }
    }
}