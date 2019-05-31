package com.anymore.example.mvvm.view

import android.animation.ObjectAnimator
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.anymore.example.R
import com.anymore.example.databinding.ActivitySplashBinding
import com.anymore.example.mvvm.view.login.LoginActivity
import com.anymore.example.mvvm.view.main.MainActivity
import com.anymore.example.mvvm.view.register.RegisterActivity
import com.anymore.example.mvvm.viewmodel.SplashActivityViewModel
import com.anymore.mvvmkit.mvvm.base.BaseActivity
import com.anymore.mvvmkit.mvvm.lifecycle.ActivityStackManager

/**
 * Created by liuyuanmao on 2019/3/12.
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashActivityViewModel>() {

    override fun initView(savedInstanceState: Bundle?) = R.layout.activity_splash

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mViewModel.mHasLoginedUser.observe(this, Observer {
            if (it == true) {
                ActivityStackManager.instance.finishUntil(MainActivity::class.java.name)
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } else {
                showLoginAndRegister()
            }
        })
        mBinding.btnLogin.setOnClickListener { startActivity(Intent(this@SplashActivity, LoginActivity::class.java)) }
        mBinding.btnRegister.setOnClickListener {
            startActivity(
                Intent(
                    this@SplashActivity,
                    RegisterActivity::class.java
                )
            )
        }
    }

    private fun showLoginAndRegister() {
        val anim = ObjectAnimator.ofFloat(mBinding.tvAppName, "rotation", 0f, 366f)
        anim.duration = 3000
        anim.start()
        mBinding.btnLogin.visibility = View.VISIBLE
        mBinding.btnRegister.visibility = View.VISIBLE
    }
}