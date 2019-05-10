package com.anymore.example.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.anymore.example.app.config.AppConfig
import com.anymore.example.mvvm.model.MainModel
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.view.web.ExtendedWebActivity.Companion.FONT_SMALL
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.SingleLiveEvent
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/5/6.
 */
@ActivityScope
class ExtendedWebActivityViewModel @Inject constructor(application: Application,private val mModel:MainModel)
    : BaseViewModel(application) {
    val mToast by lazy { SingleLiveEvent<String>() }
    val webViewTextSize by lazy { MutableLiveData<Int>() }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        loadSavedWebViewTextSize()
    }

    fun saveWebViewTextSize(size:Int){
        Timber.d("saveWebViewTextSize")
        AppConfig.saveWebViewTextSize(size)
        loadSavedWebViewTextSize()
    }

    private fun loadSavedWebViewTextSize(){
        Timber.d("loadSavedWebViewTextSize")
        webViewTextSize.value = AppConfig.getWebViewTextSize(FONT_SMALL)
    }

    fun collectAticle(article: Article){
        val disposable =  mModel.collectArticle(article.id)
            .subscribeBy(onNext = {
                mToast.value = if (it){"收藏成功!"}else{"收藏失败!"}
            },onError = {
                Timber.e(it)
                mToast.value = it.message
            })
        addToCompositeDisposable(disposable)
    }



}