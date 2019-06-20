package com.anymore.example.mvvm.model.api

import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.lang.IllegalArgumentException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * 描述列表加载状态
 * Created by liuyuanmao on 2019/4/19.
 */

@Suppress("DataClassPrivateConstructor")
data class NetStatus private constructor(val status: Status,val message:String?=null){
    companion object {
        val SUCCESS=NetStatus(Status.SUCCESS,"加载完成")
        val DOING = NetStatus(Status.DOING,"正在加载,请稍候....")
        fun failed(throwable: Throwable= Throwable("请求失败"))=NetStatus(Status.FAILED, parseThrowable(throwable))
        private fun parseThrowable(e: Throwable): String {
            return when(e){
                is SocketTimeoutException,is ConnectException, is HttpException->"网络连接异常"
                is JsonParseException,is JSONException,is ParseException->"数据解析异常"
                is UnknownHostException->"服务器连接异常"
                is IllegalArgumentException->"参数异常"
                else->"发生了未知错误:${e.message}"
            }
        }
    }
}

enum class Status{
    SUCCESS,
    FAILED,
    DOING
}