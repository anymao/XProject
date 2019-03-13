package com.anymore.simple.mvvm.view

import android.animation.ObjectAnimator
import android.os.Bundle
import com.anymore.mvvmkit.mvvm.base.BindingActivity
import com.anymore.simple.R
import com.anymore.simple.databinding.ActivitySplashBinding

/**
 * Created by liuyuanmao on 2019/3/12.
 */
class SplashActivity:BindingActivity<ActivitySplashBinding>() {

    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_splash

    override fun initData(savedInstanceState: Bundle?) {
        val anim = ObjectAnimator.ofFloat(mBinding.tvAppName,"rotation",0f,366f)
        anim.duration = 3000
        anim.start()
    }
}