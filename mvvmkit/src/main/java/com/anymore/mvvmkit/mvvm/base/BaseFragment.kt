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
abstract class BaseFragment<BD:ViewDataBinding,VM: BaseViewModel>:
    BindingFragment<BD>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    protected lateinit var mViewModel: VM

    @CallSuper
    override fun initData(savedInstanceState: Bundle?) {
        Timber.d("initData")
        //通过反射获取ViewModel的实际类型Clazz
        @Suppress("UNCHECKED_CAST")
        initViewModel((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>)
    }


    /**
     * 初始化ViewModel操作，默认的Viewmodel周期与当前Fragment一致,
     * 如果想让当前ViewModel的周期与其Activity一致，需要传入其容器Activity而非本身
     * <p>
     *     <code>
     *          override fun initViewModel(clazz: Class<TodoTabActivityViewModel>) {
     *               mViewModel = ViewModelProviders.of(activity!!,mViewModelFactory).get(clazz)
     *               mViewModel.let { lifecycle.addObserver(it) }
     *          }
     *     </code>
     */
    open fun initViewModel(clazz: Class<VM>) {
        mViewModel = ViewModelProviders.of(this,mViewModelFactory).get(clazz)
        mViewModel.let { lifecycle.addObserver(it) }
    }

    override fun onDestroyView() {
        Timber.d("onDestroyView")
        lifecycle.removeObserver(mViewModel)
        super.onDestroyView()
    }

    override fun injectable()=true
}