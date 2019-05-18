package com.anymore.example.mvvm.model

import android.app.Application
import android.support.annotation.IntRange
import com.anymore.example.mvvm.model.api.APIParamsBuilder
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.example.mvvm.model.api.WanAndroidTodoApi
import com.anymore.example.mvvm.model.entry.Todos
import com.anymore.example.mvvm.model.exception.WanAndroidException
import com.anymore.mvvmkit.mvvm.base.BaseModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by anymore on 2019/5/10.
 */
class TodoModel @Inject constructor(application: Application)
    :BaseModel(application){
//    private val mDao by lazy { mRepositoryComponent.getRepository().obtainRoomDatabase() }

    private val mApi by lazy { mRepositoryComponent.getRepository().obtainRetrofitService(KEY,WanAndroidTodoApi::class.java) }

    fun getTodoList(@IntRange(from = 1) page:Int = 1,type: Int):Observable<Todos>{
        return mApi.getTodoList(page,APIParamsBuilder.getTodoListParams(type = type))
            .map {
                if (it.errorCode == 0){
                    return@map it.data
                }else{
                    throw WanAndroidException(it.errorMsg?:"获取待办列表失败")
                }
            }
    }
}