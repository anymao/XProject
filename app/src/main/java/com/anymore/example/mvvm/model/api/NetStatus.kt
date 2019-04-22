package com.anymore.example.mvvm.model.api

/**
 * 描述列表加载状态
 * Created by liuyuanmao on 2019/4/19.
 */

@Suppress("DataClassPrivateConstructor")
data class NetStatus private constructor(val status: Status,val message:String?=null){
    companion object {
        val SUCCESS=NetStatus(Status.SUCCESS)
        val DOING = NetStatus(Status.DOING)
        fun failed(message: String="请求失败!")=NetStatus(Status.FAILED,message)
    }
}

enum class Status{
    SUCCESS,
    FAILED,
    DOING
}