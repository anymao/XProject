package com.anymore.example.mvvm.model.paging.article

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.anymore.example.mvvm.model.api.NetStatus
import com.anymore.example.mvvm.model.entry.Article
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

/**
 * Created by anymore on 2019/4/7.
 */
class ArticlesSource(private val mApi: ArticlesApiWrapper) : PageKeyedDataSource<Int, Article>(){

    private var mRetry:(()->Unit)?=null
    val mStatus = MutableLiveData<NetStatus>()

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        Timber.d("loadInitial:")
        mStatus.postValue(NetStatus.DOING)
        mApi.loadInitial(page = 0)
            .subscribeBy(onNext = {
                val next = if (it.curPage < it.pageCount){
                    it.curPage
                }else{
                    null
                }
                callback.onResult(it.datas,null,next)
                mRetry = null
                mStatus.postValue(NetStatus.SUCCESS)
            },onError = {
                mRetry = {
                    loadInitial(params,callback)
                }
                mStatus.postValue(NetStatus.failed(it.message?:"加载数据失败!"))
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        Timber.d("loadAfter  call:${params.key}")
        mStatus.postValue(NetStatus.DOING)
        mApi.loadAfter(page = params.key)
            .subscribeBy(onNext = {
                val next = if (it.curPage < it.pageCount){
                    it.curPage
                }else{
                    null
                }
                callback.onResult(it.datas,next)
                mRetry = null
                mStatus.postValue(NetStatus.SUCCESS)
            },onError = {
                mRetry = {
                    loadAfter(params,callback)
                }
                mStatus.postValue(NetStatus.failed(it.message?:"加载数据失败!"))
            })
    }

    @SuppressLint("CheckResult")
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        Timber.d("loadBefore  call:${params.key}")
        mStatus.postValue(NetStatus.DOING)
        mApi.loadAfter(page = params.key)
            .subscribeBy(onNext = {
                val previous = if (it.curPage > 0){
                    it.curPage-1
                }else{
                    null
                }
                callback.onResult(it.datas,previous)
                mRetry = null
                mStatus.postValue(NetStatus.SUCCESS)
            },onError = {
                mRetry = {
                    loadAfter(params,callback)
                }
                mStatus.postValue(NetStatus.failed(it.message?:"加载数据失败!"))
            })
    }

    fun retry()=mRetry
}