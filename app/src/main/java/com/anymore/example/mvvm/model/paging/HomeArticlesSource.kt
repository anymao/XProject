package com.anymore.example.mvvm.model.paging

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.anymore.example.mvvm.model.api.NetStatus
import com.anymore.example.mvvm.model.api.WanAndroidHomePageApi
import com.anymore.example.mvvm.model.entry.HomeArticle
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

/**
 * Created by anymore on 2019/4/7.
 */
class HomeArticlesSource(private val mApi:WanAndroidHomePageApi) : PageKeyedDataSource<Int, HomeArticle>(){

    private var mRetry:(()->Unit)?=null
    val mStatus = MutableLiveData<NetStatus>()

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, HomeArticle>) {
        Timber.d("loadInitial:")
        mStatus.postValue(NetStatus.DOING)
        mApi.getArticles(page = 0)
            .subscribeBy(onNext = {
                if (it.errorCode == 0 && it.data != null){
                    val next = if (it.data.curPage < it.data.pageCount){
                        it.data.curPage
                    }else{
                        null
                    }
                    callback.onResult(it.data.datas,null,next)
                    mRetry = null
                    mStatus.postValue(NetStatus.SUCCESS)
                }else{
                    mRetry = {
                        loadInitial(params,callback)
                    }
                    mStatus.postValue(NetStatus.failed(it.errorMsg?:"加载数据失败!"))
                }
            },onError = {
                mRetry = {
                    loadInitial(params,callback)
                }
                mStatus.postValue(NetStatus.failed(it.message?:"加载数据失败!"))
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, HomeArticle>) {
        Timber.d("loadAfter  call:${params.key}")
        mStatus.postValue(NetStatus.DOING)
        mApi.getArticles(page = params.key)
            .subscribeBy(onNext = {
                if (it.errorCode == 0 && it.data != null){
                    val next = if (it.data.curPage < it.data.pageCount){
                        it.data.curPage
                    }else{
                        null
                    }
                    callback.onResult(it.data.datas,next)
                    mRetry = null
                    mStatus.postValue(NetStatus.SUCCESS)
                }else{
                    mRetry = {
                        loadAfter(params,callback)
                    }
                    mStatus.postValue(NetStatus.failed(it.errorMsg?:"加载数据失败!"))
                }
            },onError = {
                mRetry = {
                    loadAfter(params,callback)
                }
                mStatus.postValue(NetStatus.failed(it.message?:"加载数据失败!"))
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, HomeArticle>) {

    }

    fun retry()=mRetry

}