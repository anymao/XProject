package com.anymore.example.mvvm.view.web

import android.os.Bundle
import android.text.TextUtils
import com.anymore.example.R
import com.anymore.example.databinding.ActivityUrlBinding
import com.anymore.example.ext.toast
import com.anymore.mvvmkit.mvvm.base.BindingActivity

/**
 * Created by liuyuanmao on 2019/5/27.
 */
class UrlActivity: BindingActivity<ActivityUrlBinding>() {


    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_url

    override fun initData(savedInstanceState: Bundle?) {
        mBinding.btnGo.setOnClickListener {
            val url = mBinding.etUrl.text.toString().trim()
            if (!TextUtils.isEmpty(url)){
                WebActivity.start(this,url)
            }else{
                toast("url不能为空!")
            }
        }
    }
}