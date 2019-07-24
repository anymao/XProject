package com.anymore.client.ui.mvp.login.fragment

import android.os.Bundle
import com.anymore.client.R
import com.anymore.client.ui.mvp.login.LoginContract
import com.anymore.mvpkit.exts.toast
import com.anymore.mvpkit.mvp.base.BaseMvpFragment
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by liuyuanmao on 2019/7/24.
 */
class LoginFragment :BaseMvpFragment<LoginContract.IPresenter>(),LoginContract.IView{


    override fun getLayoutRes()= R.layout.activity_login

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        btn_login.setOnClickListener {
            btn_login.setOnClickListener {
                val username:String = et_username.text.toString().trim()
                val password:String = et_password.text.toString().trim()
                mPresenter.login(username,password)
            }
        }
    }

    override fun loginSuccess() {
        toast("hhh,LoginFragment Login Success!")
    }
}