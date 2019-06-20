package com.anymore.example.mvvm.model.paging.todo

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.anymore.example.app.executors.AppExecutors
import com.anymore.example.mvvm.model.api.NetStatus
import com.anymore.example.mvvm.model.entry.Todo
import com.anymore.example.mvvm.model.paging.Retry
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import java.util.concurrent.Executor

/**
 * Created by anymore on 2019/4/7.
 */
class TodoSource(private val mApi: TodoApiWrapper) : PageKeyedDataSource<Int, Todo>(){

    val mRetry = MutableLiveData<Retry?>()
    val mStatus = MutableLiveData<NetStatus>()

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Todo>) {
        Timber.d("loadInitial:")
        mStatus.postValue(NetStatus.DOING)
        mApi.loadInitial(page = 1)
            .subscribeBy(onNext = {
                val next = if (it.curPage < it.pageCount){
                    it.curPage
                }else{
                    null
                }
                callback.onResult(it.datas,null,next)
                mRetry.postValue(null)
                mStatus.postValue(NetStatus.SUCCESS)
            },onError = {
                mRetry.postValue {
                    getRetryExecutor().execute {
                        loadInitial(params,callback)
                    }
                }
                mStatus.postValue(NetStatus.failed(it))
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Todo>) {
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
                mRetry.postValue(null)
                mStatus.postValue(NetStatus.SUCCESS)
            },onError = {
                mRetry.postValue {
                    getRetryExecutor().execute {
                        loadAfter(params,callback)
                    }
                }
                mStatus.postValue(NetStatus.failed(it))
            })
    }

    @SuppressLint("CheckResult")
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Todo>) {
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
                mRetry.postValue(null)
                mStatus.postValue(NetStatus.SUCCESS)
            },onError = {
                mRetry.postValue {
                    getRetryExecutor().execute {
                        loadAfter(params,callback)
                    }
                }
                mStatus.postValue(NetStatus.failed(it))
            })
    }


    //DataSource自己正常加载的时候，是运行在内部自己的线程池中的，
    // 但是重试的时候，线程池需要我们自己指定，不然默认在主线程执行，会造成NetworkOnMainThreadException
    private fun getRetryExecutor(): Executor = AppExecutors.networkExecutor

}