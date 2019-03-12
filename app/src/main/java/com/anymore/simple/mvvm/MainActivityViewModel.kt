package com.anymore.simple.mvvm

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/3/11.
 */
@ActivityScope
class MainActivityViewModel @Inject constructor(application: Application,model: MainModel):BaseViewModel<MainModel>(application, model) {

    private val mMessage = MutableLiveData<String>()
    val mData = MutableLiveData<List<Article>>()
    override fun onStart() {
        super.onStart()
        mMessage.postValue("hello")
        val disposable = mModel.doGet()
            .subscribeBy(onNext = {
                Timber.d(it.toString())
                mData.postValue(it)
                }
                ,onError = {
                    Timber.e(it)
                })
        addToCompositeDisposable(disposable)
    }

    fun getMessage()=mMessage
}