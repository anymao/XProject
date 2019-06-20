package com.anymore.example.mvvm.view.adapter

import android.arch.paging.PagedListAdapter
import android.support.annotation.LayoutRes
import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import com.anymore.example.R
import com.anymore.example.mvvm.model.api.NetStatus
import com.anymore.example.mvvm.view.adapter.viewholder.BindingViewHolder

/**
 * 带网络状态和重试按钮的Adapter
 * Created by liuyuanmao on 2019/6/20.
*/
abstract class NetStatusPagingAdapter<T>(config: AsyncDifferConfig<T>) :
    PagedListAdapter<T, BindingViewHolder>(config) {

    var netStatus:NetStatus?=null
        set(value) {
            val previousStatus = field
            val hadStatusRow = hasStatusRow()
            field = value
            val hasStatusRow = hasStatusRow()
            if (hadStatusRow != hasStatusRow) {
                if (hadStatusRow) {
                    notifyItemRemoved(super.getItemCount())
                } else {
                    notifyItemInserted(super.getItemCount())
                }
            } else if (hasStatusRow && previousStatus != netStatus) {
                notifyItemChanged(itemCount - 1)
            }
        }


    override fun getItemCount()=super.getItemCount()+ if (hasStatusRow()) 1 else 0

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


}