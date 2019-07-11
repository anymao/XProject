package com.anymore.example.mvvm.view.adapter

import android.content.Context
import android.support.v7.util.DiffUtil
import com.anymore.example.BR
import com.anymore.example.R
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.view.adapter.viewholder.BindingViewHolder

class ArticlesPagingAdapter(context: Context) :
    NetStatusPagingAdapter<Article>(diffCallback) {

    var mItemEventHandler :OnItemEventHandler?=null

    override fun getItemViewLayout(position: Int) = R.layout.home_article_item

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position < getDataItemCount()){
            holder.mBinding.setVariable(BR.entry, getItem(position))
            mItemEventHandler?.let {
                holder.mBinding.setVariable(BR.eventHandler, it)
            }
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
        fun onCollectClick(item: Article)
    }
}

