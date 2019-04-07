package com.anymore.example.mvvm.view.main

import com.anymore.example.R
import com.anymore.example.databinding.FragmentMyBinding
import com.anymore.example.mvvm.viewmodel.MyFragmentViewModel
import com.anymore.mvvmkit.mvvm.base.BaseFragment

class MyFragment:BaseFragment<FragmentMyBinding,MyFragmentViewModel>() {

    override fun getLayoutRes()= R.layout.fragment_my
}