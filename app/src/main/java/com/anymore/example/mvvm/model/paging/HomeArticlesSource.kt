package com.anymore.example.mvvm.model.paging

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.anymore.example.mvvm.model.api.NetStatus
import com.anymore.example.mvvm.model.api.WanAndroidHomePageApi
import com.anymore.example.mvvm.model.entry.HomeArticle
import com.anymore.example.mvvm.model.exception.WanAndroidException
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

/**
 * Created by anymore on 2019/4/7.
 */
class HomeArticlesSource(private val mHomePageApi:WanAndroidHomePageApi) : PageKeyedDataSource<Int, HomeArticle>(){

    private var mRetry :(()->Unit)?=null

    val mStatus = MutableLiveData<NetStatus>()

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, HomeArticle>) {
        Timber.d("loadInitial $params")
        mStatus.postValue(NetStatus.DOING)
        mHomePageApi.getArticles(page = 0)
            .subscribeBy(onNext = {
                if (it.errorCode == 0){
                    if (it.data?.datas != null && !it.data.datas.isEmpty()){
                        val nextPage = if (it.data.curPage < it.data.pageCount){
                            it.data.curPage+1
                        }else{
                            null
                        }
                        callback.onResult(it.data.datas,null,nextPage)
                        mRetry = null
                        mStatus.postValue(NetStatus.SUCCESS)
                    }else{
                        throw WanAndroidException("请求数据为空!")
                    }
                }else{
                    throw WanAndroidException(it.errorMsg ?: "请求数据为空!")
                }
            },onError = {
                mRetry = {
                    loadInitial(params,callback)
                }
                mStatus.postValue(NetStatus.failed(it.message?:"请求失败!"))
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, HomeArticle>) {
        Timber.d("loadAfter $params")
        mStatus.postValue(NetStatus.DOING)
        mHomePageApi.getArticles(page = params.key)
            .subscribeBy(onNext = {
                if (it.errorCode == 0){
                    if (it.data?.datas != null && !it.data.datas.isEmpty()){
                        val nextPage = if (it.data.curPage < it.data.pageCount){
                            it.data.curPage+1
                        }else{
                            null
                        }
                        callback.onResult(it.data.datas,nextPage)
                        mRetry = null
                        mStatus.postValue(NetStatus.SUCCESS)
                    }else{
                        throw WanAndroidException("请求数据为空!")
                    }
                }else{
                    throw WanAndroidException(it.errorMsg ?: "请求数据为空!")
                }
            },onError = {
                mRetry = {
                    loadAfter(params,callback)
                }
                mStatus.postValue(NetStatus.failed(it.message?:"请求失败!"))
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, HomeArticle>) {

    }

    fun retry() = mRetry
}