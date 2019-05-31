package com.anymore.example.mvvm.view.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anymore.example.BR
import com.anymore.example.R
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.view.adapter.viewholder.BindingViewHolder

class ArticlesPagingAdapter(private val mContext: Context) :
    PagedListAdapter<Article, BindingViewHolder>(diffCallback) {

    private val mLayoutInflater by lazy { LayoutInflater.from(mContext) }
    var mItemEventHandler: Any? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder(DataBindingUtil.inflate(mLayoutInflater, R.layout.article_item, parent, false))
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.mBinding.setVariable(BR.entry, getItem(position))
        mItemEventHandler?.let {
            holder.mBinding.setVariable(BR.eventHandler, it)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Article>() {

            override fun areItemsTheSame(oldItem: Article, newItem: Article) = (oldItem.id == newItem.id)

            override fun areContentsTheSame(oldItem: Article, newItem: Article) = (oldItem.id == newItem.id)
        }
    }

    interface OnItemEventHandler {
        fun onClick(item: Article)
    }
}

