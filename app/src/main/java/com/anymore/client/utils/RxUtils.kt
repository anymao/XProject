package com.anymore.client.utils

import com.anymore.mvpkit.mvp.base.BaseContract
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by liuyuanmao on 2019/7/22.
 */
fun <T> Observable<T>.withloading(view: BaseContract.IBaseView, message: String = "",cancelable:Boolean = false): Observable<T> {
    val transformer = ObservableTransformer<T, T> {
        it.doOnSubscribe { view.showProgressBar(message, cancelable) }
            .subscribeOn(AndroidSchedulers.mainThread())
            .doFinally { view.hideProgressBar() }
    }
    return compose(transformer)
}