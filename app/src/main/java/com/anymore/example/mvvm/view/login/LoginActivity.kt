package com.anymore.example.mvvm.view.login

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.anymore.example.R
import com.anymore.example.databinding.ActivityLoginBinding
import com.anymore.example.ext.toast
import com.anymore.example.mvvm.view.main.MainActivity
import com.anymore.example.mvvm.viewmodel.LoginViewModel
import com.anymore.example.utils.UiUtils
import com.anymore.mvvmkit.mvvm.base.BaseActivity
import com.anymore.mvvmkit.mvvm.lifecycle.ActivityStackManager

/**
 * Created by liuyuanmao on 2019/3/29.
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun initView(savedInstanceState: Bundle?) = R.layout.activity_login

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        UiUtils.setupToolbar(this, mBinding.toolbar)
        mViewModel.mErrorMessage.observe(this, Observer { toast(it, Toast.LENGTH_LONG) })
        mViewModel.mMessage.observe(this, Observer { toast(it, Toast.LENGTH_LONG) })
        mViewModel.mLoginEvent.observe(this, Observer {
            ActivityStackManager.instance.finishUntil(MainActivity::class.java.name)
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        })
        mBinding.btnLogin.setOnClickListener {
            mViewModel.login(
                mBinding.etUserName.text.toString(),
                mBinding.etPwd.text.toString()
            )
        }
    }
}