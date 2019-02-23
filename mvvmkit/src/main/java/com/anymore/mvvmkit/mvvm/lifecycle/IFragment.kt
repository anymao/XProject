package com.anymore.mvvmkit.mvvm.lifecycle

import android.os.Bundle
import android.support.annotation.LayoutRes

/**
 * Created by liuyuanmao on 2019/2/23.
 */
interface IFragment {

    @LayoutRes
    fun getLayoutRes():Int
    fun initView(savedInstanceState: Bundle?)
    fun initData(savedInstanceState: Bundle?)
    //以下三个方法提供给ActivityWrapper使用，ActivityWrapper在Application.ActivityLifecycleCallbacks中被自动装载
    fun useEventBus()=false
    fun injectable()=false
}