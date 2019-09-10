package com.anymore.mvpkit.exts

import com.anymore.mvpkit.mvp.base.BaseContract
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * RxJava2相关的扩展操作
 * Created by liuyuanmao on 2019/7/22.
 *
 * Presenter经常进行的耗时操作时，View层会进行弹窗提示，此方法进行扩展封装
 * doFinally方法的调用时机：onComplete、onError或者的下游dispose的时候
 * 因此非常适合开启和关闭弹窗
 * for [Observable]
 */
fun <T> Observable<T>.withLoading(view: BaseContract.IBaseView, message: String = "", cancelable:Boolean = false): Observable<T> {
   return observeOn(AndroidSchedulers.mainThread())
       .doOnSubscribe {
           view.showProgressBar(message,cancelable)
       }
       .doFinally {
           view.hideProgressBar()
       }
}

/**
 * extension for [Completable]
 */
fun Completable.withLoading(view: BaseContract.IBaseView, message: String = "", cancelable:Boolean = false): Completable {
    return observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {
            view.showProgressBar(message,cancelable)
        }
        .doFinally {
            view.hideProgressBar()
        }
}


/**
 * extension for [Single]
 */
fun <T> Single<T>.withLoading(view: BaseContract.IBaseView, message: String = "", cancelable:Boolean = false): Single<T> {
    return observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {
            view.showProgressBar(message,cancelable)
        }
        .doFinally {
            view.hideProgressBar()
        }
}

/**
 * 线程切换，在io线程池订阅，在Main线程观察
 */
fun <T> Observable<T>.io2Main():Observable<T>{
    val transformer = ObservableTransformer<T,T> {
        it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    return compose(transformer)
}