package com.anymore.example.mvvm.viewmodel

import android.app.Application
import com.anymore.example.mvvm.model.TodoModel
import com.anymore.mvvmkit.di.scope.FragmentScope
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by anymore on 2019/5/10.
 */
@FragmentScope
class TodoFragmentViewModel @Inject constructor(application: Application,private val mModel:TodoModel)
    :BaseViewModel(application) {
}