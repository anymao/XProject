package com.anymore.example.mvvm.view.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.anymore.example.R
import com.anymore.example.databinding.ActivityMainBinding
import com.anymore.example.mvvm.view.adapter.FragmentsAdapter
import com.anymore.example.mvvm.viewmodel.MainActivityViewModel
import com.anymore.mvvmkit.mvvm.base.BaseActivity

/**
 * Created by liuyuanmao on 2019/3/11.
 */
class MainActivity:BaseActivity<ActivityMainBinding, MainActivityViewModel>(){

    private lateinit var mFragments:List<Fragment>

    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        mFragments = listOf(HomeFragment())
        val fragmentAdapter = FragmentsAdapter(supportFragmentManager,mFragments)
        mBinding.viewPager.adapter = fragmentAdapter
    }
}