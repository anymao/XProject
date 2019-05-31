package com.anymore.example.mvvm.view.todo

/**
 * Created by liuyuanmao on 2019/5/27.
 */
data class PagedListing<T>(
    val list: List<T>,
    val isRefresh: Boolean,
    val isLoadMore: Boolean,
    val hasMore: Boolean,
    val success: Boolean = true
)