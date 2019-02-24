package com.anymore.mvvmkit.mvvm.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.anymore.mvvmkit.mvvm.lifecycle.activity.IActivity

/**
 * Created by liuyuanmao on 2019/2/20.
 */
abstract class BindingActivity<B:ViewDataBinding> :AppCompatActivity(),
    IActivity {
    protected lateinit var mBinding:B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,initView(savedInstanceState))
        initData(savedInstanceState)
    }
}