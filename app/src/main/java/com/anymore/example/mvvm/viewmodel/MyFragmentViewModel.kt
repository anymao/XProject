package com.anymore.example.mvvm.viewmodel

import android.app.Application
import com.anymore.example.mvvm.model.MainModel
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import javax.inject.Inject

class MyFragmentViewModel @Inject constructor(private val mModel: MainModel, application: Application) :
    BaseViewModel(application) {
}