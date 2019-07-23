package com.anymore.client.utils

import com.anymore.mvpkit.mvp.base.BaseContract
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by liuyuanmao on 2019/7/22.
 */
fun <T> Observable<T>.withLoading(view: BaseContract.IBaseView, message: String = "", cancelable:Boolean = false): Observable<T> {
    val transformer = ObservableTransformer<T, T> {
        it.subscribeOn(Schedulers.io())
            .doOnSubscribe { view.showProgressBar(message, cancelable) }
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { view.hideProgressBar() }
    }
    return compose(transformer)
}

//fun <T> Observable<T>.io2Main():Observable<T>{
//
//}