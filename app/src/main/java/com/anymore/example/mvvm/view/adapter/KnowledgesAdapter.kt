package com.anymore.example.mvvm.view.adapter

import android.content.Context
import com.anymore.example.R
import com.anymore.example.mvvm.model.entry.Knowledge

/**
 * Created by liuyuanmao on 2019/4/28.
 */
class KnowledgesAdapter(mContext: Context, mEventHandler: OnItemEventHandler?=null) :
    BindingRecyclerViewAdapter<Knowledge>(mContext, R.layout.app_item_knowledge) {

    init {
        mItemEventHandler = mEventHandler
    }

    interface OnItemEventHandler{
        fun onClick(item: Knowledge)
    }
}