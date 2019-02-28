package com.anymore.simple.mvvm.view

import android.os.Bundle
import com.anymore.mvvmkit.mvvm.base.BindingFragment
import com.anymore.simple.R
import com.anymore.simple.databinding.FragmentTestBinding
import timber.log.Timber

/**
 * Created by liuyuanmao on 2019/2/27.
 */
class TestVisiableFragment:BindingFragment<FragmentTestBinding>(){
    override fun getLayoutRes()= R.layout.fragment_test

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mBinding.text.text = "${this.hashCode()}"
        Timber.d("initData")
    }
}
