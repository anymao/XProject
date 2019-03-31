package com.anymore.example.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.anymore.example.mvvm.model.MainModel
import com.anymore.example.mvvm.model.entry.Banner
import com.anymore.mvvmkit.di.scope.FragmentScope
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import javax.inject.Inject

@FragmentScope
class HomeFragmentViewModel @Inject constructor(application: Application,private val mModel:MainModel)
    :BaseViewModel(application){

    val mBanners by  lazy { MutableLiveData<List<Banner>>() }

    init {

    }

    override fun onDestroy() {
        super.onDestroy()
        mModel.onClear()
    }
}