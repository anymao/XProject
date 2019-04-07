package com.anymore.example.mvvm.view.main

import com.anymore.example.R
import com.anymore.example.databinding.FragmentDiscoveryBinding
import com.anymore.example.mvvm.viewmodel.DiscoveryFragmentViewModel
import com.anymore.mvvmkit.mvvm.base.BaseFragment

class DiscoveryFragment:BaseFragment<FragmentDiscoveryBinding,DiscoveryFragmentViewModel>() {

    override fun getLayoutRes()= R.layout.fragment_discovery

}