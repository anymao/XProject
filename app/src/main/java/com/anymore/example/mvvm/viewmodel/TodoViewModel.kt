package com.anymore.example.mvvm.viewmodel

import android.app.Application
import com.anymore.example.mvvm.model.TodoModel
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import javax.inject.Inject

/**
 * TodoFragmentViewModel让TodoTabActivity持有
 * Created by anymore on 2019/5/10.
 */
@ActivityScope
class TodoViewModel @Inject constructor(application: Application, private val mModel: TodoModel) :
    BaseViewModel(application) {



}