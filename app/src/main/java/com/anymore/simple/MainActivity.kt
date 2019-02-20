package com.anymore.simple

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import com.anymore.mvvmkit.mvvm.BaseActivity
import com.anymore.mvvmkit.mvvm.BaseViewModel
import com.anymore.mvvmkit.mvvm.BindingActivity
import com.anymore.simple.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding,BaseViewModel>() {
    override fun initView(savedInstanceState: Bundle?)=R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel = ViewModelProviders.of(this).get(BaseViewModel::class.java)
        mBinding.bt1.setOnClickListener { Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show() }
    }

}
