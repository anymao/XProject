package com.anymore.mvvmkit.mvvm

import android.databinding.ViewDataBinding
import android.os.Bundle

/**
 * Created by liuyuanmao on 2019/2/20.
 */
abstract class BaseActivity<B:ViewDataBinding,VM:BaseViewModel>:BindingActivity<B>(){
    protected lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel?.let { lifecycle.addObserver(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel?.let { lifecycle.removeObserver(it) }
    }
}