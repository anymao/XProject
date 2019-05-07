package com.anymore.example.mvvm.view.knowledges

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.anymore.example.R
import com.anymore.example.databinding.FragmentKnowledgesArticlesBinding
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.view.FragmentActivity
import com.anymore.example.mvvm.view.adapter.ArticlesPagingAdapter
import com.anymore.example.mvvm.view.web.WebActivity
import com.anymore.example.mvvm.viewmodel.KnowledgesArticlesFragmentViewModel
import com.anymore.mvvmkit.mvvm.base.BaseFragment

/**
 * Created by liuyuanmao on 2019/4/30.
 */
class KnowledgesArticlesFragment:BaseFragment<FragmentKnowledgesArticlesBinding,KnowledgesArticlesFragmentViewModel>(){

    companion object {
        const val EXTRA_CID = "EXTRA_CID"
        fun newInstance(cid:Int):KnowledgesArticlesFragment{
            val bundle = Bundle()
            bundle.putInt(EXTRA_CID,cid)
            val fragment = KnowledgesArticlesFragment()
            fragment.arguments = bundle
            return fragment
        }

        fun start(context: Context, cid: Int,title:String=""){
            val bundle = Bundle()
            bundle.putInt(EXTRA_CID,cid)
            FragmentActivity.start(context,KnowledgesArticlesFragment::class.java.name,title,bundle)
        }
    }

    private val mAdapter by lazy { ArticlesPagingAdapter(context!!) }

    override fun getLayoutRes() = R.layout.fragment_knowledges_articles

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        initRecycleView()
        val cid = getCid()
        mViewModel.getKnowledgesArticlesListing(cid).pagedList.observe(this, Observer { mAdapter.submitList(it) })
    }

    private fun getCid(): Int {
        val bundle = arguments
        val cid = bundle?.getInt(EXTRA_CID)?:0
        return cid
    }

    private fun initRecycleView() {
        mBinding.rvArticles.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        mBinding.rvArticles.layoutManager = LinearLayoutManager(context)
        mAdapter.mItemEventHandler = object :ArticlesPagingAdapter.OnItemEventHandler{
            override fun onClick(item: Article) {
                WebActivity.start(context!!,item.link)
            }
        }
        mBinding.rvArticles.adapter = mAdapter
    }

}