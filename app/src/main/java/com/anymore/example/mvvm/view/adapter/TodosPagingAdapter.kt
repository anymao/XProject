package com.anymore.example.mvvm.view.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anymore.example.BR
import com.anymore.example.R
import com.anymore.example.mvvm.model.entry.Todo
import com.anymore.example.mvvm.view.adapter.viewholder.BindingViewHolder

class TodosPagingAdapter(private val mContext: Context) : PagedListAdapter<Todo, BindingViewHolder>(diffCallback) {

    private val mLayoutInflater by lazy { LayoutInflater.from(mContext) }
    var mItemEventHandler: Any? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder(DataBindingUtil.inflate(mLayoutInflater, R.layout.todo_item, parent, false))
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.mBinding.setVariable(BR.entry, getItem(position))
        mItemEventHandler?.let {
            holder.mBinding.setVariable(BR.eventHandler, it)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Todo>() {

            override fun areItemsTheSame(oldItem: Todo, newItem: Todo) = (oldItem.id == newItem.id)

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo) = (oldItem.id == newItem.id)
        }
    }

    interface OnItemEventHandler {
        fun onClick(item: Todo)
    }
}

