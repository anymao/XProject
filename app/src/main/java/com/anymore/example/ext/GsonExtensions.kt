package com.anymore.example.ext

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