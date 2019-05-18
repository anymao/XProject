package com.anymore.example.mvvm.model.paging.todo

import android.annotation.SuppressLint
import android.arch.paging.PagedList
import com.anymore.example.mvvm.model.api.APIParamsBuilder
import com.anymore.example.mvvm.model.api.WanAndroidTodoApi
import com.anymore.example.mvvm.model.db.entry.Todo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

/**
 * Created by liuyuanmao on 2019/5/16.
*/
class TodoBoundaryCallback(private val api:WanAndroidTodoApi) :PagedList.BoundaryCallback<Todo>(){

    @SuppressLint("CheckResult")
    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        api.getTodoList(1,APIParamsBuilder.getTodoListParams())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {

                },
                onError = {

                }
            )
    }

    override fun onItemAtEndLoaded(itemAtEnd: Todo) {
        super.onItemAtEndLoaded(itemAtEnd)
    }

}