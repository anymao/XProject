package com.anymore.example.ext

import android.app.Activity
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by anymore on 2019/4/20.
 */

inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)


inline fun  <reified T> Gson.toList(json: String):List<T>{
    return fromJson<List<T>>(json, object : TypeToken<List<T>>() {
    }.type)
}

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