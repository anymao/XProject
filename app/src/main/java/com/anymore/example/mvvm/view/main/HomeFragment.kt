package com.anymore.example.mvvm.view.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.anymore.example.R
import com.anymore.example.databinding.FragmentHomeBinding
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.model.entry.Banner
import com.anymore.example.mvvm.view.adapter.ArticlesPagingAdapter
import com.anymore.example.mvvm.view.adapter.BannerLoader
import com.anymore.example.mvvm.view.web.ExtendedWebActivity
import com.anymore.example.mvvm.viewmodel.HomeFragmentViewModel
import com.anymore.mvvmkit.mvvm.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {


    private lateinit var mBannerLoader: BannerLoader
    private val mAdapter by lazy {
        ArticlesPagingAdapter(context!!).apply {
            mItemEventHandler = object : ArticlesPagingAdapter.OnItemEventHandler {
                override fun onClick(item: Article) {
                    ExtendedWebActivity.start(context!!, item)
                }

                override fun onCollectClick(item: Article) {
                    with(item){
                        collect = !collect
                    }
                }
            }
        }
    }

    override fun getLayoutRes() = R.layout.fragment_home

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        initBanner()
        initRecyclerView()
        mViewModel.mBanners.observe(this, Observer { it?.run { setBanners(this) } })
        mViewModel.mArticleListing.pagedList.observe(this, Observer { mAdapter.submitList(it) })
        mViewModel.mArticleListing.status.observe(this, Observer { mAdapter.setNetStatus(it) })
        mViewModel.mArticleListing.retry.observe(this, Observer { mAdapter.retry = it })
    }

    override fun onResume() {
        super.onResume()
        mBannerLoader.startAutoPlay()
    }

    override fun onPause() {
        mBannerLoader.stopAutoPlay()
        super.onPause()
    }

    private fun initBanner() {
        mBannerLoader = BannerLoader(mBinding.banner)
        mBannerLoader.initBanner {
            ExtendedWebActivity.start(context!!, it.url)
        }
    }

    private fun setBanners(banners: List<Banner>) {
        mBannerLoader.loadData(banners)
    }


    private fun initRecyclerView() {
        mBinding.rvArticles.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        mBinding.rvArticles.layoutManager = LinearLayoutManager(context)
        mBinding.rvArticles.adapter = mAdapter
    }
}