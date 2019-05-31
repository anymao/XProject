package com.anymore.example.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.anymore.example.mvvm.model.MainModel
import com.anymore.example.mvvm.model.entry.Knowledge
import com.anymore.mvvmkit.di.scope.FragmentScope
import com.anymore.mvvmkit.mvvm.SingleLiveEvent
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@FragmentScope
class DiscoveryFragmentViewModel @Inject constructor(private val mModel: MainModel, application: Application) :
    BaseViewModel(application) {
    val mAllKnowledges = MutableLiveData<List<Knowledge>>()
    val mToast = SingleLiveEvent<CharSequence>()


    fun loadAllKnowledges() {
        val disposable = mModel.getAllKnowledges()
            .subscribeBy(onNext = {
                mAllKnowledges.value = it
            }, onError = {
                mToast.value = it.message
            })
        addToCompositeDisposable(disposable)
    }
}