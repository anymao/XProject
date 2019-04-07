package com.anymore.example.mvvm.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anymore.example.BR
import com.anymore.example.mvvm.view.adapter.viewholder.BindingViewHolder

open class BindingRecyclerViewAdapter<T>(protected val mContext:Context,@LayoutRes protected var defaultItemLayout :Int? = null):RecyclerView.Adapter<BindingViewHolder>() {

    private val mData by lazy { ArrayList<T>() }
    private val mLayoutInflater by lazy { LayoutInflater.from(mContext) }
    var mItemEventHandler:Any? = null
    fun setData(data:List<T>){
        mData.clear()
        addData(data)
    }

    fun addData(data: List<T>) {
        mData.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val itemLayout = defaultItemLayout?:viewType
        return BindingViewHolder(DataBindingUtil.inflate(mLayoutInflater,itemLayout,parent,false))
    }

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.mBinding.setVariable(BR.entry,mData[position])
        mItemEventHandler?.let {
            holder.mBinding.setVariable(BR.eventHandler,it)
        }
    }


}