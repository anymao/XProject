package com.anymore.example.mvvm.viewmodel

import android.app.Application
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.SingleLiveEvent
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/5/6.
 */
@ActivityScope
class ExtendedWebActivityViewModel @Inject constructor(application: Application)
    : BaseViewModel(application) {
    val mToast by lazy { SingleLiveEvent<String>() }



}