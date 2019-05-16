package com.anymore.example.mvvm.model.paging.todo

import android.arch.paging.PagedList
import com.anymore.example.mvvm.model.db.entry.Todo

/**
 * Created by liuyuanmao on 2019/5/16.
*/
class TodoCallback :PagedList.BoundaryCallback<Todo>(){
    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Todo) {
        super.onItemAtEndLoaded(itemAtEnd)
    }

    override fun onItemAtFrontLoaded(itemAtFront: Todo) {
        super.onItemAtFrontLoaded(itemAtFront)
    }
}