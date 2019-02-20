package com.anymore.mvvmkit.mvvm.lifecycle

import android.app.Activity
import android.os.Bundle
import org.greenrobot.eventbus.EventBus

/**
 * Created by liuyuanmao on 2019/2/20.
 */
class ActivityWrapper(private var mActivity:Activity,private var iActivity: IActivity) :IActivityLifecycle{

    override fun onCreate(savedInstanceState: Bundle?) {
        if (iActivity.useEventBus()){
            EventBus.getDefault().register(mActivity)
        }
    }

    override fun onDestroy() {
        if (iActivity.useEventBus()){
            EventBus.getDefault().unregister(mActivity)
        }
    }
}