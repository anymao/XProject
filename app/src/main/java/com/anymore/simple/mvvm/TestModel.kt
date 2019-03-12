package com.anymore.simple.mvvm

import android.app.Application
import com.anymore.mvvmkit.mvvm.base.BaseModel
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

import com.anymore.simple.mvvm.KEY

/**
 * Created by liuyuanmao on 2019/3/12.
 */
class TestModel {


    fun get(): Observable<String> {
        return Observable.just(1, 2, 3)
            .flatMap({ integer:Int -> Observable.just("" + integer!!) } as Function<Int, ObservableSource<String>>)
    }
}
