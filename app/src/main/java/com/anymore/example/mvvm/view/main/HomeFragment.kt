package com.anymore.example.mvvm.view.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.anymore.example.R
import com.anymore.example.databinding.FragmentHomeBinding
import com.anymore.example.mvvm.model.entry.Banner
import com.anymore.example.mvvm.viewmodel.HomeFragmentViewModel
import com.anymore.mvvmkit.mvvm.base.BaseFragment

class HomeFragment:BaseFragment<FragmentHomeBinding,HomeFragmentViewModel>() {

    override fun getLayoutRes()= R.layout.fragment_home

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mViewModel.mBanners.observe(this, Observer { it?.run {setBanners(this)} })
    }

    private fun setBanners(banners: List<Banner>) {

    }
}