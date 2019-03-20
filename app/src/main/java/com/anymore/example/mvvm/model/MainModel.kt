package com.anymore.example.mvvm.model

import android.app.Application
import com.anymore.mvvmkit.mvvm.base.BaseModel
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.example.mvvm.model.api.WanAndroid
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/3/12.
 */
class MainModel @Inject constructor(application: Application):BaseModel(application) {

    fun doGet():Observable<List<Article>>{
        return mRepositoryComponent.getRepository()
            .obtainRetrofitService(KEY, WanAndroid::class.java)
            .getWeixinArticleList()
            .subscribeOn(Schedulers.newThread())
            .flatMap{
                Observable.just(it.data!!)

            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}