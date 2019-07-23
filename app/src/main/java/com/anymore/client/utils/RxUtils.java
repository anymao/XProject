package com.anymore.client.utils;

import com.anymore.mvpkit.mvp.base.BaseContract;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuyuanmao on 2019/7/23.
 */
public class RxUtils {

    public static <T> ObservableTransformer<T, T> withLoading(final BaseContract.IBaseView view, final String message,final boolean cancelable) {
        //隐藏进度条
        return observable -> observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    view.showProgressBar(message,cancelable);//显示进度条
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(view::hideProgressBar);
    }
}
