package com.anymore.mvpkit.exts

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast
import com.anymore.mvpkit.di.component.RepositoryComponent
import com.anymore.mvpkit.mvp.base.BaseContract
import com.anymore.mvpkit.mvp.lifecycle.KitApplication
import com.anymore.mvpkit.widget.ToastUtils
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by liuyuanmao on 2019/3/12.
 */

fun Application.getRepositoryComponent(): RepositoryComponent {
    if (this is KitApplication){
        return getRepositoryComponent()
    }
    throw RuntimeException("your application:<${this.javaClass.name}> should extends KitApplication")
}

fun Context.getRepositoryComponent():RepositoryComponent{
    return (applicationContext as Application).getRepositoryComponent()
}


/**
 * 判断网络是否连接
 */
fun Context.isNetworkConnected():Boolean{
    val networkService: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info: NetworkInfo? = networkService.activeNetworkInfo
    return info?.isAvailable?:false
}

fun Context.sp2px(spValue: Int): Int {
    val fontScale = resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}

fun Context.dp2px(dpValue: Int):Int{
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun Activity.toast(@StringRes id:Int, duration: Int = Toast.LENGTH_SHORT){
    toast(getString(id),duration)
}

fun Activity.toast(message:CharSequence?, duration: Int = Toast.LENGTH_SHORT){
    ToastUtils.show(this,message,duration)
}


fun Fragment.toast(@StringRes id:Int, duration: Int = Toast.LENGTH_SHORT){
    toast(getString(id),duration)
}

fun Fragment.toast(message:CharSequence?, duration: Int = Toast.LENGTH_SHORT){
    ToastUtils.show(context!!,message,duration)
}

fun <T> withLoading(view:BaseContract.IBaseView,title:String="正在加载..."):ObservableTransformer<T,T>{
    return ObservableTransformer { observable ->
        observable.subscribeOn(Schedulers.io())
            .doOnSubscribe {
                view.showProgressBar(title)//显示进度条
            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                view.hideProgressBar()//隐藏进度条
            }
    }
}