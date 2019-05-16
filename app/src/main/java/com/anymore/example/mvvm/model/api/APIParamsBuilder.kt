package com.anymore.example.mvvm.model.api

import android.support.annotation.IntRange
import android.support.annotation.NonNull

/**
 * Created by liuyuanmao on 2019/5/16.
 */
object APIParamsBuilder{

    fun postTodoParams(@NonNull title:String,
                       @NonNull content:String,
                       date:String,
                       @IntRange(from = 1) type:Int,
                       @IntRange(from = 1) priority:Int):Map<String,Any>{
        val param = HashMap<String,Any>()
        param["title"] = title
        param["content"] = content
        param["date"] = date
        param["type"] = type
        param["priority"] = priority
        return param
    }

    fun getTodoListParams(status :Int?=null,
                          type :Int?=null,
                          priority:Int?=null,
                          orderby:Int?=4):MutableMap<String,Any>{
        val param = HashMap<String,Any>()
        status?.let {param["status"] = it}
        type?.let { param["type"] = it }
        priority?.let { param["priority"] = it }
        orderby?.let { param["orderby"] = it }
        return param
    }


}
