package com.anymore.mvvmkit.mvvm.lifecycle

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection
import org.greenrobot.eventbus.EventBus

/**
 * Created by liuyuanmao on 2019/2/23.
 */
class FragmentWrapper(private val mFragment: Fragment,private val iFragment: IFragment):IFragmentLifecycle{
    override fun onAttach(context: Context) {
        if (iFragment.useEventBus()){
            EventBus.getDefault().register(mFragment)
        }
        if (iFragment.injectable()){
            AndroidSupportInjection.inject(mFragment)
        }
    }


    override fun onActivityCreate(savedInstanceState: Bundle?) {
        iFragment.initData(savedInstanceState)
    }


    override fun onDetach() {
        if (iFragment.useEventBus()){
            EventBus.getDefault().unregister(mFragment)
        }
    }

    override fun isAdded()=mFragment?.isAdded


}