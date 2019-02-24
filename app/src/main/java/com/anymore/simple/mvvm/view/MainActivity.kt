package com.anymore.simple.mvvm.view

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.anymore.mvvmkit.mvvm.base.BaseActivity
import com.anymore.simple.R
import com.anymore.simple.databinding.ActivityMainBinding
import com.anymore.simple.mvvm.viewmodel.MainViewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mBinding.bt1.setOnClickListener {
            Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,SecondActivity::class.java))
        }
        mViewModel.getMessage().observe(this, Observer<String> {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })
    }

}
