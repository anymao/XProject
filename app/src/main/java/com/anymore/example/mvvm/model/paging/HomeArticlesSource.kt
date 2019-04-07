package com.anymore.example.mvvm.model.paging

import android.annotation.SuppressLint
import android.app.Application
import android.arch.paging.DataSource
import android.arch.paging.PositionalDataSource
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.example.mvvm.model.api.WanAndroidHomePageApi
import com.anymore.example.mvvm.model.entry.HomeArticle
import com.anymore.example.mvvm.model.exception.WanAndroidException
import com.anymore.mvvmkit.getRepositoryComponent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by anymore on 2019/4/7.
 */
class HomeArticlesSource(private val application: Application) :PositionalDataSource<HomeArticle>(){

    private val mRepository by lazy { application.getRepositoryComponent().getRepository() }
    private val mHomePageApi by lazy { mRepository.obtainRetrofitService(KEY,WanAndroidHomePageApi::class.java) }

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<HomeArticle>) {
        mHomePageApi.getArticles(params.requestedStartPosition)
            .subscribeOn(Schedulers.io())
            .flatMap {
                if (it.errorCode == 0 && it.data != null){
                    Observable.just(Pair(it.data.curPage,it.data.datas))
                }else{
                    Observable.error(WanAndroidException(it.errorMsg?:"获取首页文章失败!"))
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    callback.onResult(it.second,params.requestedStartPosition)
                },onError = {
                    Timber.e(it)
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<HomeArticle>) {
        mHomePageApi.getArticles(params.startPosition)
            .subscribeOn(Schedulers.io())
            .flatMap {
                if (it.errorCode == 0 && it.data != null){
                    Observable.just(Pair(it.data.curPage,it.data.datas))
                }else{
                    Observable.error(WanAndroidException(it.errorMsg?:"获取首页文章失败!"))
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    callback.onResult(it.second)
                },onError = {
                    Timber.e(it)
                }
            )
    }


    class Factory(private val mApplication: Application):DataSource.Factory<Int,HomeArticle>(){
        override fun create(): DataSource<Int, HomeArticle> = HomeArticlesSource(mApplication)
    }
}