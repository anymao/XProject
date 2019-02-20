package com.anymore.mvvmkit.mvvm.lifecycle

import android.os.Bundle
import android.support.annotation.LayoutRes

/**
 * Created by liuyuanmao on 2019/2/20.
 */
interface IActivity {
    @LayoutRes
    fun initView(savedInstanceState :Bundle?):Int
    fun initData(savedInstanceState: Bundle?)

    fun useFragment()=false
    fun useEventBus()=false
    fun injectable()=false
}