package com.anymore.example.mvvm.view.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anymore.example.BR
import com.anymore.example.R
import com.anymore.example.mvvm.model.api.NetStatus
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.model.paging.Retry
import com.anymore.example.mvvm.view.adapter.viewholder.BindingViewHolder

class ArticlesPagingAdapter(private val mContext: Context) :
    PagedListAdapter<Article, BindingViewHolder>(diffCallback) {

    private val mLayoutInflater by lazy { LayoutInflater.from(mContext) }
    var mItemEventHandler: Any? = null
    private var mNetStatus:NetStatus?=null
    private val retryProxy = object :PagingAdapterRetry{
        override fun retry() {
               retry?.invoke()
        }
    }
    var retry:Retry?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder(DataBindingUtil.inflate(mLayoutInflater, viewType, parent, false))
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        if (position < super.getItemCount()){
            holder.mBinding.setVariable(BR.entry, getItem(position))
            mItemEventHandler?.let {
                holder.mBinding.setVariable(BR.eventHandler, it)
            }
        }else{
            holder.mBinding.setVariable(BR.status,mNetStatus)
            holder.mBinding.setVariable(BR.retry,retryProxy)
        }
    }

    override fun getItemCount()=super.getItemCount()+ if (hasNetStatusRow()) 1 else 0

    fun setNetStatus(status: NetStatus?){
        val previousStatus = mNetStatus
        val hadNetStatusRow = hasNetStatusRow()
        mNetStatus = status
        val hasNetStatusRow = hasNetStatusRow()
        if (hadNetStatusRow != hasNetStatusRow) {
            if (hadNetStatusRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasNetStatusRow && previousStatus != mNetStatus) {
            notifyItemChanged(itemCount - 1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasNetStatusRow() && position == itemCount - 1) {
            R.layout.retry_item
        } else {
            R.layout.home_article_item
        }
    }


    private fun hasNetStatusRow()=mNetStatus!=null && mNetStatus != NetStatus.SUCCESS

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

    interface PagingAdapterRetry{
        fun retry()
    }
}

