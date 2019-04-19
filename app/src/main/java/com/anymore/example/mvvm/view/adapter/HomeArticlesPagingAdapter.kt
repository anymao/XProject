package com.anymore.example.mvvm.view.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anymore.example.BR
import com.anymore.example.R
import com.anymore.example.mvvm.model.entry.HomeArticle
import com.anymore.example.mvvm.view.adapter.viewholder.BindingViewHolder

class HomeArticlesPagingAdapter(private val mContext:Context):PagedListAdapter<HomeArticle,BindingViewHolder>(diffCallback){

    private val mLayoutInflater by lazy { LayoutInflater.from(mContext) }
    var mItemEventHandler:Any? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BindingViewHolder {
        return BindingViewHolder(DataBindingUtil.inflate(mLayoutInflater, R.layout.home_article_item,p0,false))
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.mBinding.setVariable(BR.entry,getItem(position))
        mItemEventHandler?.let {
            holder.mBinding.setVariable(BR.eventHandler,it)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<HomeArticle>() {

            override fun areItemsTheSame(p0: HomeArticle, p1: HomeArticle)=(p0.id == p1.id)

            override fun areContentsTheSame(p0: HomeArticle, p1: HomeArticle)=(p0.id == p1.id)
        }
    }
}

