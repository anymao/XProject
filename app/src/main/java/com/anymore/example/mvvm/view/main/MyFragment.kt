package com.anymore.example.mvvm.view.main

import android.content.Intent
import android.os.Bundle
import com.anymore.example.R
import com.anymore.example.databinding.FragmentMyBinding
import com.anymore.example.mvvm.view.collect.CollectedArticlesActivity
import com.anymore.example.mvvm.view.todo.TodoTabActivity
import com.anymore.example.mvvm.viewmodel.MyFragmentViewModel
import com.anymore.mvvmkit.mvvm.base.BaseFragment

class MyFragment:BaseFragment<FragmentMyBinding,MyFragmentViewModel>() {

    override fun getLayoutRes()= R.layout.fragment_my

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mBinding.tvTodo.setOnClickListener { startActivity(Intent(context,TodoTabActivity::class.java)) }
        mBinding.tvCollected.setOnClickListener { startActivity(Intent(context,CollectedArticlesActivity::class.java)) }
    }
}