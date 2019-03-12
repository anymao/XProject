package com.anymore.mvvmkit.mvvm.base

import android.arch.lifecycle.ViewModelProviders
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import com.anymore.mvvmkit.mvvm.ViewModelFactory
import timber.log.Timber
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/2/20.
 */
abstract class BaseActivity<B:ViewDataBinding,VM: BaseViewModel<*>>:
    BindingActivity<B>(){
    protected lateinit var mViewModel: VM

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        mViewModel.let { lifecycle.removeObserver(it) }
        super.onDestroy()
    }

    @CallSuper
    override fun initData(savedInstanceState: Bundle?) {
        @Suppress("UNCHECKED_CAST")
        mViewModel = ViewModelProviders.of(this,mViewModelFactory).get((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>)
        mViewModel.let { lifecycle.addObserver(it) }
    }

    override fun injectable()=true
//    private class ClassParser<VM>{
//        fun getViewModelClass()=(javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
//    }
}