package com.anymore.example.mvvm.view.register

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.anymore.example.R
import com.anymore.example.databinding.ActivityRegisterBinding
import com.anymore.example.ext.toast
import com.anymore.example.mvvm.viewmodel.RegisterActivityViewModel
import com.anymore.example.utils.UiUtils
import com.anymore.mvvmkit.mvvm.base.BaseActivity

/**
 * Created by liuyuanmao on 2019/3/20.
 */
class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterActivityViewModel>() {

    override fun initView(savedInstanceState: Bundle?) = R.layout.activity_register

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        UiUtils.setupToolbar(this, mBinding.toolbar)
        mViewModel.mErrorMessage.observe(this, Observer { toast(it) })
        mViewModel.mMessage.observe(this, Observer { toast(it) })
        mBinding.btnRegister.setOnClickListener {
            mViewModel.register(
                mBinding.etUserName.text.toString(),
                mBinding.etPwd.text.toString(),
                mBinding.etRePwd.text.toString()
            )
        }
    }

}