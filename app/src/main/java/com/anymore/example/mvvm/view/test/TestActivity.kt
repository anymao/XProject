package com.anymore.example.mvvm.view.test

import android.os.Bundle
import com.anymore.example.R
import com.anymore.example.app.glide.GlideApp
import com.anymore.example.databinding.ActivityTestBinding
import com.anymore.mvvmkit.mvvm.base.BindingActivity

/**
 * Created by liuyuanmao on 2019/6/12.
 */
class TestActivity:BindingActivity<ActivityTestBinding>() {
    private val url = "http://psz93ybnf.bkt.clouddn.com/image/jpg/car.jpg"

    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_test

    override fun initData(savedInstanceState: Bundle?) {
        GlideApp.with(this)
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.drawable.ic_me_1)
            .into(mBinding.ivImage)
    }
}