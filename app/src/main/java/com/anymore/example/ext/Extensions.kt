package com.anymore.example.ext

import android.app.Activity
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast
import com.anymore.example.app.executors.AppExecutors
import com.anymore.example.mvvm.view.event.LoadingEvent
import com.anymore.mvvmkit.mvvm.SingleLiveEvent
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by anymore on 2019/4/20.
 */

inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)


inline fun <reified T> Gson.toList(json: String):List<T>{
    return fromJson<List<T>>(json, object : TypeToken<List<T>>() {
    }.type)
}

/**
 * 扩展Activity的Toast方法
 */
fun Activity.toast(@StringRes id:Int,duration: Int = Toast.LENGTH_SHORT){
    toast(getString(id),duration)
}

fun Activity.toast(message:CharSequence?,duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,message,duration).show()
}


fun Fragment.toast(@StringRes id:Int,duration: Int = Toast.LENGTH_SHORT){
    toast(getString(id),duration)
}

fun Fragment.toast(message:CharSequence?,duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(context,message,duration).show()
}

fun runOnUiThread(block:()->Unit){
    AppExecutors.mainExecutor.execute(block)
}

//扩展BaseViewModel，具备toast的观察事件
val BaseViewModel.toastEvent:SingleLiveEvent<CharSequence> by lazy { SingleLiveEvent<CharSequence>() }

//扩展BaseViewModel，使View层具备观察Viewmodel加载状态的能力
val BaseViewModel.loadingEvent:SingleLiveEvent<LoadingEvent> by lazy { SingleLiveEvent<LoadingEvent>() }