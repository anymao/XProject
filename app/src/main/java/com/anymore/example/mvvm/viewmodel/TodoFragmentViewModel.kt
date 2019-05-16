package com.anymore.example.mvvm.viewmodel

import android.app.Application
import android.support.annotation.IntRange
import com.anymore.example.mvvm.model.TodoModel
import com.anymore.example.mvvm.model.entry.Todo
import com.anymore.example.mvvm.model.paging.Listing
import com.anymore.example.mvvm.model.paging.todo.TodoRepository
import com.anymore.mvvmkit.di.scope.FragmentScope
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by anymore on 2019/5/10.
 */
@FragmentScope
class TodoFragmentViewModel @Inject constructor(application: Application,private val mModel:TodoModel)
    :BaseViewModel(application) {

    private val repository by lazy { TodoRepository(application) }

    fun loadTodoList(@IntRange(from = 0,to = 1) type:Int):Listing<Todo>{
        return if (type == 0){ repository.getUnfinishedTodoList() }else{ repository.getFinishedTodoList() }
    }
}