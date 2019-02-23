package com.anymore.mvvmkit.mvvm

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anymore.mvvmkit.mvvm.lifecycle.IFragment

/**
 * Created by liuyuanmao on 2019/2/23.
 */
abstract class BindingFragment<BD:ViewDataBinding>:Fragment(),IFragment {

    protected lateinit var mBinding: BD

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater,getLayoutRes(),container,false)
        initView(savedInstanceState)
        return mBinding.root
    }

}