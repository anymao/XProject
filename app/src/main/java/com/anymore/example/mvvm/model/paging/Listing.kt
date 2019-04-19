package com.anymore.example.mvvm.model.paging

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.anymore.example.mvvm.model.api.NetStatus

/**
 * Created by liuyuanmao on 2019/4/19.
 */
data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val status:LiveData<NetStatus>,
    val retry:()->Unit
)