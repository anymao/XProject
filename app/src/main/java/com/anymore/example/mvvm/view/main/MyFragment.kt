package com.anymore.example.mvvm.view.main

import android.content.Intent
import android.os.Bundle
import com.anymore.example.R
import com.anymore.example.databinding.FragmentMyBinding
import com.anymore.example.mvvm.view.collect.CollectedArticlesActivity
import com.anymore.example.mvvm.view.todo.TodoTabActivity
import com.anymore.example.mvvm.view.web.WebActivity
import com.anymore.example.mvvm.viewmodel.MyFragmentViewModel
import com.anymore.mvvmkit.mvvm.base.BaseFragment

class MyFragment:BaseFragment<FragmentMyBinding,MyFragmentViewModel>() {

    override fun getLayoutRes()= R.layout.fragment_my

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mBinding.ivHead.setOnClickListener { WebActivity.start(context!!,"http://192.168.24.89:8888/demo/test.html") }
//        mBinding.ivHead.setOnClickListener { WebActivity.start(context!!,"https://baijiahao.baidu.com/s?id=1626142328178390593&wfr=content") }
        mBinding.tvTodo.setOnClickListener { startActivity(Intent(context,TodoTabActivity::class.java)) }
        mBinding.tvCollected.setOnClickListener { startActivity(Intent(context,CollectedArticlesActivity::class.java)) }
    }
}