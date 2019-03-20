package com.anymore.example.mvvm.view.register

import android.os.Bundle
import com.anymore.example.R
import com.anymore.example.databinding.ActivityRegisterBinding
import com.anymore.mvvmkit.mvvm.base.BaseActivity
import com.anymore.mvvmkit.mvvm.base.BaseViewModel

/**
 * Created by liuyuanmao on 2019/3/20.
 */
class RegisterActivity:BaseActivity<ActivityRegisterBinding,BaseViewModel<*>>() {

    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_register

}