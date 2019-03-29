package com.anymore.example.mvvm.view.login

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.anymore.example.R
import com.anymore.example.databinding.ActivityLoginBinding
import com.anymore.example.mvvm.viewmodel.LoginViewModel
import com.anymore.mvvmkit.mvvm.base.BaseActivity

/**
 * Created by liuyuanmao on 2019/3/29.
 */
class LoginActivity:BaseActivity<ActivityLoginBinding,LoginViewModel>(){

    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_login

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        mBinding.toolbar.setNavigationOnClickListener{finish()}
        mViewModel.mErrorMessage.observe(this, Observer { Toast.makeText(this@LoginActivity,it, Toast.LENGTH_LONG).show() })
        mViewModel.mMessage.observe(this, Observer { Toast.makeText(this@LoginActivity,it, Toast.LENGTH_LONG).show() })
        mBinding.btnLogin.setOnClickListener {
            mViewModel.login(mBinding.etUserName.text.toString(),
                mBinding.etPwd.text.toString())
        }
    }
}