package com.anymore.simple.mvvm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.anymore.mvvmkit.mvvm.base.BaseActivity
import com.anymore.simple.R
import com.anymore.simple.databinding.ActivityMainBinding

/**
 * Created by liuyuanmao on 2019/3/11.
 */
class MainActivity:BaseActivity<ActivityMainBinding,MainActivityViewModel>(){
    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mViewModel.getMessage().observe(this, Observer { Toast.makeText(this,it,Toast.LENGTH_LONG).show() })
    }
}