package com.anymore.example.mvvm.view.collect

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.anymore.example.R
import com.anymore.example.databinding.ActivityCollectedArticlesBinding
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.view.adapter.ArticlesPagingAdapter
import com.anymore.example.mvvm.view.web.WebActivity
import com.anymore.example.mvvm.viewmodel.CollectedArticlesActivityViewModel
import com.anymore.example.utils.UiUtils
import com.anymore.mvvmkit.mvvm.base.BaseActivity

/**
 * Created by liuyuanmao on 2019/5/6.
 */
class CollectedArticlesActivity : BaseActivity<ActivityCollectedArticlesBinding, CollectedArticlesActivityViewModel>() {

    private val mAdapter by lazy { ArticlesPagingAdapter(this) }

    override fun initView(savedInstanceState: Bundle?) = R.layout.activity_collected_articles

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        UiUtils.setupToolbar(this, mBinding.toolbar)
        initRecycleView()
        mViewModel.listing.pagedList.observe(this, Observer { mAdapter.submitList(it) })
    }

    private fun initRecycleView() {
        mBinding.rvArticles.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mBinding.rvArticles.layoutManager = LinearLayoutManager(this)
        mAdapter.mItemEventHandler = object : ArticlesPagingAdapter.OnItemEventHandler {
            override fun onClick(item: Article) {
                WebActivity.start(this@CollectedArticlesActivity, item.link)
            }
        }
        mBinding.rvArticles.adapter = mAdapter
    }
}