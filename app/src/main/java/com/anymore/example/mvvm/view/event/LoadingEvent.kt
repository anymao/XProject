package com.anymore.example.mvvm.view.event

/**
 * ViewModel层通过LoadingEvent来通知View层当前状态
 * Created by liuyuanmao on 2019/7/13.
 */
data class LoadingEvent(val loading:Boolean,val message:String = "")