package com.anymore.example.mvvm.view.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.anymore.mvvmkit.mvvm.base.BaseActivity
import com.anymore.example.R
import com.anymore.example.databinding.ActivityMainBinding
import com.anymore.example.mvvm.viewmodel.MainActivityViewModel

/**
 * Created by liuyuanmao on 2019/3/11.
 */
class MainActivity:BaseActivity<ActivityMainBinding, MainActivityViewModel>(){
    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mViewModel.getMessage().observe(this, Observer { Toast.makeText(this,it,Toast.LENGTH_LONG).show() })
        mViewModel.mData.observe(this, Observer { Toast.makeText(this,"请求成功!${it?.size ?: -1}",Toast.LENGTH_LONG).show() })
    }
}