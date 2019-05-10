package com.anymore.example.mvvm.view.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.anymore.example.R
import com.anymore.example.databinding.FragmentDiscoveryBinding
import com.anymore.example.ext.toast
import com.anymore.example.mvvm.model.entry.Knowledge
import com.anymore.example.mvvm.view.adapter.KnowledgesAdapter
import com.anymore.example.mvvm.view.knowledges.KnowledgesTabActivity
import com.anymore.example.mvvm.viewmodel.DiscoveryFragmentViewModel
import com.anymore.mvvmkit.mvvm.base.BaseFragment

class DiscoveryFragment:BaseFragment<FragmentDiscoveryBinding,DiscoveryFragmentViewModel>() {

    private val mAdapter by lazy { KnowledgesAdapter(context!!).apply {
        mItemEventHandler = object :KnowledgesAdapter.OnItemEventHandler{
            override fun onClick(item: Knowledge) {
                KnowledgesTabActivity.start(context!!,item)
            }
        }
    } }

    override fun getLayoutRes()= R.layout.fragment_discovery

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mViewModel.mAllKnowledges.observe(this, Observer { it?.run { mAdapter.setData(this) } })
        mViewModel.mToast.observe(this, Observer { toast(it) })
        initRecycleView()
        mViewModel.loadAllKnowledges()
    }

    private fun initRecycleView() {
        mBinding.rvKnowledges.layoutManager = GridLayoutManager(context,3)
        mBinding.rvKnowledges.adapter = mAdapter
    }

}