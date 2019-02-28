package com.anymore.simple.mvvm.view

import android.os.Bundle
import com.anymore.mvvmkit.mvvm.base.BindingActivity
import com.anymore.simple.R
import com.anymore.simple.databinding.ActivityContainerBinding

/**
 * Created by liuyuanmao on 2019/2/27.
 */
class ContainerActivity:BindingActivity<ActivityContainerBinding>() {
    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_container

    override fun initData(savedInstanceState: Bundle?) {
//       supportFragmentManager.beginTransaction()
//           .add(R.id.fl_container,TestVisiableFragment())
//           .commit()
    }

    override fun useFragment()=true
}