package com.anymore.example.mvvm.model.paging.todo

/**
 * Created by liuyuanmao on 2019/5/16.
 */
object TodoPostParams{

    fun getTodoListParams(status :Int?=null,
                          type :Int?=null,
                          priority:Int?=null,
                          orderby:Int?=4):Map<String,*>{
        val param = HashMap<String,Int>()
        status?.let {param["status"] = it}
        type?.let { param["type"] = it }
        priority?.let { param["priority"] = it }
        orderby?.let { param["orderby"] = it }
        return param
    }
}
