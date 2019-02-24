package com.anymore.simple.mvvm.view

import android.os.Bundle
import com.anymore.mvvmkit.mvvm.base.BindingActivity
import com.anymore.simple.R
import com.anymore.simple.databinding.ActivitySecondBinding

class SecondActivity:BindingActivity<ActivitySecondBinding>() {
    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_second

    override fun initData(savedInstanceState: Bundle?) {
        supportFragmentManager.beginTransaction().add(R.id.fl_container,TestFragment()).commit()
    }

    override fun useFragment()=true
}