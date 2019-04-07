package com.anymore.example.mvvm.view.adapter

import android.content.Context
import com.anymore.example.R
import com.anymore.example.mvvm.model.entry.HomeArticle


class HomeArticlesAdapter(context: Context,eventHandler:OnItemEventHandler? = null):BindingRecyclerViewAdapter<HomeArticle>(context, R.layout.home_article_item) {
    init {
        mItemEventHandler = eventHandler
    }

    interface OnItemEventHandler{
        fun onClick(item:HomeArticle)
    }
}