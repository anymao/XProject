package com.anymore.example.mvvm.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.annotation.LayoutRes
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anymore.example.BR
import com.anymore.example.mvvm.view.adapter.viewholder.BindingViewHolder

/**
 * 增加DiffUtil检测，在列表数据经常变动情况下使用
 * Created by anymore on 2019/5/20.
 */
open class BindingListAdapter<T>(protected val mContext: Context, @LayoutRes protected var defaultItemLayout :Int? = null,diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BindingViewHolder>(diffCallback) {

    protected val mLayoutInflater:LayoutInflater by lazy { LayoutInflater.from(mContext) }
    protected var mItemEventHandler :Any? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val itemLayout = defaultItemLayout?:viewType
        return BindingViewHolder(DataBindingUtil.inflate(mLayoutInflater,itemLayout,parent,false))
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = getItem(position)
        holder.mBinding.setVariable(BR.entry,item)
        mItemEventHandler?.let {
            holder.mBinding.setVariable(BR.eventHandler,it)
        }
    }
}