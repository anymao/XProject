package com.anymore.example.mvvm.view.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import com.anymore.example.R
import com.anymore.example.mvvm.model.entry.Todo

/**
 * Created by liuyuanmao on 2019/5/16.
 */
class TodosAdapter(context: Context, mEventHandler:OnItemEventHandler?=null , @LayoutRes val layoutRes: Int = R.layout.todo_item):BindingRecyclerViewAdapter<Todo>(context,layoutRes){

    init {
        mItemEventHandler = mEventHandler
    }

    interface OnItemEventHandler{
        fun onClick(item: Todo)
    }
}