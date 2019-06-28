package com.anymore.example.mvvm.view.adapter

import android.support.v7.util.DiffUtil
import com.anymore.example.R
import com.anymore.example.mvvm.model.db.entry.Todo

/**
 * Created by liuyuanmao on 2019/6/28.
 */
class FixTodosAdapter : NetStatusPagingAdapter<Todo>(diffCallback) {

    override fun getItemViewLayout(position: Int)= R.layout.todo_item

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Todo>() {

            override fun areItemsTheSame(oldItem: Todo, newItem: Todo) = (oldItem.id == newItem.id)

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo) = (oldItem.id == newItem.id)
        }
    }
}
