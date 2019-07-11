package com.anymore.example.mvvm.view.adapter

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.annotation.LayoutRes
import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anymore.example.BR
import com.anymore.example.R
import com.anymore.example.mvvm.model.api.NetStatus
import com.anymore.example.mvvm.model.paging.Retry
import com.anymore.example.mvvm.view.adapter.viewholder.BindingViewHolder
import timber.log.Timber

/**
 * 带网络状态和重试按钮的Adapter基类
 * Created by liuyuanmao on 2019/6/20.
*/
abstract class NetStatusPagingAdapter<T> :
    PagedListAdapter<T, BindingViewHolder> {

    protected constructor(diffCallback: DiffUtil.ItemCallback<T>) : super(diffCallback)

    protected constructor(config: AsyncDifferConfig<T>) : super(config)


    var netStatus:NetStatus?=null
        //在设置新的网络状态时候，需要更新列表下方的网络状态值
        set(value) {
            Timber.d("set netStatus!")
            //获取旧的网络状态和是否有网络状态行
            val previousStatus = field
            val hadStatusRow = hasStatusRow()
            //赋新值，计算如何更新网络状态行
            field = value
            val hasStatusRow = hasStatusRow()
            if (hadStatusRow != hasStatusRow) {
                if (hadStatusRow) {
                    notifyItemRemoved(super.getItemCount())
                } else {
                    notifyItemInserted(super.getItemCount())
                }
            } else if (hasStatusRow && previousStatus != field) {
                notifyItemChanged(itemCount - 1)
            }
        }

    //retry 操作的代理类，用于传入databinding中
    private val retryProxy = object :RetryListener{
        override fun retry() {
            retry?.invoke()
        }
    }

    var retry:Retry? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        return BindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false))
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        if (position >= super.getItemCount()){
            holder.mBinding.setVariable(BR.status,netStatus)
            holder.mBinding.setVariable(BR.retry,retryProxy)
        }
    }

    override fun getItemCount()=super.getItemCount()+ (if (hasStatusRow()) 1 else 0)

    fun getDataItemCount()=super.getItemCount()

    protected fun hasStatusRow()=netStatus!=null && netStatus!= NetStatus.SUCCESS

    final override fun getItemViewType(position: Int): Int {
        return if (hasStatusRow() && position == itemCount - 1) {
            getNetStatusViewLayout()
        } else {
            getItemViewLayout(position)
        }
    }

    abstract fun getItemViewLayout(position:Int):Int

    @LayoutRes
    open fun getNetStatusViewLayout(): Int = R.layout.retry_item

    interface RetryListener{
        fun retry()
    }
}