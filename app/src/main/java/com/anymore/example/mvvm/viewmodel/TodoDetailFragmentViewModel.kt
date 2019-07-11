//package com.anymore.example.mvvm.viewmodel
//
//import android.app.Application
//import com.anymore.example.mvvm.model.TodoModel
//import com.anymore.mvvmkit.di.scope.FragmentScope
//import com.anymore.mvvmkit.mvvm.SingleLiveEvent
//import com.anymore.mvvmkit.mvvm.base.BaseViewModel
//import javax.inject.Inject
//
///**
// * Created by liuyuanmao on 2019/5/27.
// */
//@FragmentScope
//class TodoDetailFragmentViewModel @Inject constructor(application: Application, private val mModel: TodoModel) :
//    BaseViewModel(application) {
//
//    val toast by lazy { SingleLiveEvent<String>() }
//}