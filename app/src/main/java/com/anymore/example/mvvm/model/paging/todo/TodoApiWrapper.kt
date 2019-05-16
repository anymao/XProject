package com.anymore.example.mvvm.model.paging.todo

import com.anymore.example.mvvm.model.api.WanAndroidTodoApi
import com.anymore.example.mvvm.model.entry.ResponseCode
import com.anymore.example.mvvm.model.entry.Todos
import com.anymore.example.mvvm.model.exception.WanAndroidException
import io.reactivex.Observable

/**
 * Created by anymore on 2019/5/11.
 */
interface TodoApiWrapper {
    fun loadInitial(page: Int): Observable<Todos>
    fun loadAfter(page: Int): Observable<Todos>
    fun loadBefore(page: Int): Observable<Todos>
}

class TodoListApiWrapper(private val mAPi:WanAndroidTodoApi,
                         private val map:MutableMap<String,Any>):TodoApiWrapper{

    override fun loadInitial(page: Int): Observable<Todos> {
        return mAPi.getTodoList(page,map)
            .map {
                if (it.errorCode == ResponseCode.OK){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取待办信息出错!")
                }
            }
    }

    override fun loadAfter(page: Int): Observable<Todos> {
        return mAPi.getTodoList(page,map)
            .map {
                if (it.errorCode == ResponseCode.OK){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取待办信息出错!")
                }
            }
    }

    override fun loadBefore(page: Int): Observable<Todos> {
        return mAPi.getTodoList(page,map)
            .map {
                if (it.errorCode == ResponseCode.OK){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取待办信息出错!")
                }
            }
    }
}