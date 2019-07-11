package com.anymore.example.mvvm.viewmodel

import android.app.Application
import com.anymore.example.mvvm.model.MainModel
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/3/11.
 */
@ActivityScope
class MainActivityViewModel @Inject constructor(application: Application, val mModel: MainModel) :
    BaseViewModel(application) {


}