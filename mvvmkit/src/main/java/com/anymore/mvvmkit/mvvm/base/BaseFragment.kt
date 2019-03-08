package com.anymore.mvvmkit.mvvm.base

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import timber.log.Timber
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/2/23.
 */
abstract class BaseFragment<BD:ViewDataBinding,VM: BaseViewModel<*>>:
    BindingFragment<BD>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    protected lateinit var mViewModel: VM

    @CallSuper
    override fun initData(savedInstanceState: Bundle?) {
        Timber.d("initData")
        //todo 这里或许可以采用kotlin内联函数更加优雅写出来
        @Suppress("UNCHECKED_CAST")
        mViewModel = ViewModelProviders.of(this,mViewModelFactory).get((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>)
        lifecycle.addObserver(mViewModel)
    }

    override fun onDestroyView() {
        Timber.d("onDestroyView")
        lifecycle.removeObserver(mViewModel)
        super.onDestroyView()
    }

    override fun injectable()=true
}