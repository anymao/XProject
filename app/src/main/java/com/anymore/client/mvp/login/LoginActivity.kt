package com.anymore.client.mvp.login

import android.os.Bundle
import com.anymore.client.R
import com.anymore.mvpkit.exts.toast
import com.anymore.mvpkit.mvp.base.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by liuyuanmao on 2019/7/23.
 */
class LoginActivity :BaseMvpActivity<LoginContract.IPresenter>(),LoginContract.IView{
    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        btn_login.setOnClickListener {
            val username:String = et_username.text.toString().trim()
            val password:String = et_password.text.toString().trim()
            mPresenter.login(username,password)
        }
    }

    override fun loginSuccess() {
        toast("登陆成功，hahahaha")
    }
}