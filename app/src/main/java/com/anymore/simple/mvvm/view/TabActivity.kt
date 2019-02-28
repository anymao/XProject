package com.anymore.simple.mvvm.view

import android.os.Bundle
import com.anymore.mvvmkit.mvvm.base.BindingActivity
import com.anymore.simple.R
import com.anymore.simple.databinding.ActivityTabBinding

/**
 * Created by liuyuanmao on 2019/2/27.
 */
class TabActivity:BindingActivity<ActivityTabBinding>() {
    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_tab

    override fun initData(savedInstanceState: Bundle?) {
        var fragments = arrayListOf(TestVisiableFragment()
            , TestVisiableFragment(), TestVisiableFragment()
        )
        val adapter = ViewPagerAdapter(supportFragmentManager,fragments)

        mBinding.vp.adapter = adapter
    }

    override fun useFragment()=true
}