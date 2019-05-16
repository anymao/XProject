package com.anymore.example.mvvm.model.paging.todo

import android.app.Application
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.example.mvvm.model.api.WanAndroidTodoApi
import com.anymore.mvvmkit.getRepositoryComponent

/**
 * Created by anymore on 2019/5/11.
 */
class TodoRepository(private val mApplication: Application){
    private val mApi by lazy{mApplication.getRepositoryComponent()
        .getRepository()
        .obtainRetrofitService(KEY,WanAndroidTodoApi::class.java)}

    fun getUnfinishedTodoList(){
        val apiWrapper = TodoWrappedApi(mApi,0,1,1,1)
    }
}