package com.anymore.example.mvvm.view.adapter

import android.content.Context
import com.anymore.example.R
import com.anymore.example.mvvm.view.widget.OptionsDialog

/**
 * Created by liuyuanmao on 2019/5/7.
 */
class OptionsAdapter(
    mContext: Context,
    mEventHandler: OnItemEventHandler? = null,
    defaultItemLayout: Int? = R.layout.option_item
) :
    BindingRecyclerViewAdapter<OptionsDialog.OptionItem>(mContext, defaultItemLayout) {

    init {
        mItemEventHandler = mEventHandler
    }

    interface OnItemEventHandler {
        fun onClick(item: OptionsDialog.OptionItem)
    }
}