package com.anymore.mvvmkit.mvvm

import android.databinding.ViewDataBinding
import android.os.Bundle
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/2/20.
 */
abstract class BaseActivity<B:ViewDataBinding,VM:BaseViewModel>:BindingActivity<B>(){
    protected lateinit var mViewModel: VM

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)
        mViewModel?.let { lifecycle.addObserver(it) }
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        super.onDestroy()
        mViewModel?.let { lifecycle.removeObserver(it) }
    }

    override fun injectable()=true
}