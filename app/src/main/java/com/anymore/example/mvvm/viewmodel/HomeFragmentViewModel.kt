package com.anymore.example.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.anymore.example.mvvm.model.MainModel
import com.anymore.example.mvvm.model.entry.Banner
import com.anymore.example.mvvm.model.paging.ArticlesRepository
import com.anymore.mvvmkit.di.scope.FragmentScope
import com.anymore.mvvmkit.mvvm.SingleLiveEvent
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@FragmentScope
class HomeFragmentViewModel @Inject constructor(application: Application,private val mModel:MainModel)
    :BaseViewModel(application){

    val mBanners by  lazy { MutableLiveData<List<Banner>>() }
    val mErrorMessage by lazy { SingleLiveEvent<CharSequence>() }

    val mArticleListing by lazy { ArticlesRepository(application).getHomeArticlesListing()}



    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        loadBanners()
    }




    private fun loadBanners(){
        val  disposable = mModel.getHomePageBanners()
            .subscribeBy(
                onNext = { mBanners.value = it },
                onError = {Timber.e(it)}
            )
        addToCompositeDisposable(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        mModel.onClear()
    }
}