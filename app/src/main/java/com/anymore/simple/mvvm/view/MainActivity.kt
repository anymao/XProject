package com.anymore.simple.mvvm.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import com.anymore.mvvmkit.mvvm.BaseActivity
import com.anymore.simple.mvvm.viewmodel.MainViewModel
import com.anymore.simple.R
import com.anymore.simple.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d(mViewModelFactory?.toString())
    }

    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mBinding.bt1.setOnClickListener { Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show() }
        mViewModel.getMessage().observe(this, Observer<String> {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })
    }

}
