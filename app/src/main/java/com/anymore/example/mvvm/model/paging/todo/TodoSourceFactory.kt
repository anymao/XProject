package com.anymore.example.mvvm.model.paging.todo

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.anymore.example.mvvm.model.entry.Todo

/**
 * Created by anymore on 2019/5/11.
 */
class TodoSourceFactory(private val mApiWrapper: TodoApiWrapper): DataSource.Factory<Int, Todo>() {
    val source = MutableLiveData<TodoSource>()
    override fun create(): DataSource<Int, Todo> {
        val s = TodoSource(mApi = mApiWrapper)
        source.value = s
        return s
    }
}