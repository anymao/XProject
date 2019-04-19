package com.anymore.mvvmkit.mvvm.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anymore.mvvmkit.mvvm.lifecycle.fragment.IFragment
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by liuyuanmao on 2019/2/23.
 */
abstract class BindingFragment<BD:ViewDataBinding>:Fragment(), IFragment {

    protected lateinit var mBinding: BD
    private val TAG = "BindingFragment@${hashCode()}"
    //用于存储当前Fragment的前一个可见状态
    private var mPreviousVisibleState = AtomicBoolean(false)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater,getLayoutRes(),container,false)
        mBinding.lifecycleOwner = this
        initView(savedInstanceState)
        return mBinding.root
    }

    override fun onResume() {
        Timber.tag(TAG).w("${hashCode()}:onResume()")
        super.onResume()
        setFragmentVisibleState(true)
    }

    override fun onPause() {
        Timber.tag(TAG).w("${hashCode()}:onPause()")
        super.onPause()
        setFragmentVisibleState(false)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        Timber.tag(TAG).w("${hashCode()}:setUserVisibleHint($isVisibleToUser)")
        super.setUserVisibleHint(isVisibleToUser)
        setFragmentVisibleState(userVisibleHint)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        Timber.tag(TAG).w("${hashCode()}:onHiddenChanged($hidden)")
        super.onHiddenChanged(hidden)
        setFragmentVisibleState(!hidden)
    }

    private fun setFragmentVisibleState(newState:Boolean){
        if (mPreviousVisibleState.compareAndSet(!newState,newState)){
            onVisibleStateChanged(newState)
        }
    }

    protected open fun onVisibleStateChanged(visible:Boolean){
        Timber.tag(TAG).w("Fragment(${this.hashCode()})可见性：$visible")
    }

}