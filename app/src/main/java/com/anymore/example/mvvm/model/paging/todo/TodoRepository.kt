package com.anymore.example.mvvm.model.paging.todo

import android.app.Application
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.anymore.example.mvvm.model.api.APIParamsBuilder
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.example.mvvm.model.api.WanAndroidTodoApi
import com.anymore.example.mvvm.model.entry.Todo
import com.anymore.example.mvvm.model.paging.Listing
import com.anymore.mvvmkit.getRepositoryComponent

/**
 * Created by anymore on 2019/5/11.
 */
class TodoRepository(private val mApplication: Application){
    private val mApi by lazy{mApplication.getRepositoryComponent()
        .getRepository()
        .obtainRetrofitService(KEY,WanAndroidTodoApi::class.java)}

    fun getUnfinishedTodoList():Listing<Todo>{
        val apiWrapper = TodoListApiWrapper(mApi,APIParamsBuilder.getTodoListParams(status = 0))
        val factory = TodoSourceFactory(apiWrapper)
        val data = LivePagedListBuilder<Int, Todo>(factory,
            PagedList.Config
                .Builder()
                .setPageSize(20)
                .setPrefetchDistance(20)
                .build())
            .build()

        return Listing<Todo>(
            pagedList = data,
            status = Transformations.switchMap(factory.source) {
                it.mStatus
            },
            retry = {
                factory.source.value?.retry()
            }
        )
    }

    fun getFinishedTodoList():Listing<Todo>{
        val apiWrapper = TodoListApiWrapper(mApi,APIParamsBuilder.getTodoListParams(status = 1))
        val factory = TodoSourceFactory(apiWrapper)
        val data = LivePagedListBuilder<Int, Todo>(factory,
            PagedList.Config
                .Builder()
                .setPageSize(20)
                .setPrefetchDistance(20)
                .build())
            .build()

        return Listing<Todo>(
            pagedList = data,
            status = Transformations.switchMap(factory.source) {
                it.mStatus
            },
            retry = {
                factory.source.value?.retry()
            }
        )
    }
}