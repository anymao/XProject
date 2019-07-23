package com.anymore.mvpkit.mvp.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anymore.mvpkit.exts.toast
import com.anymore.mvpkit.mvp.lifecycle.fragment.IFragment
import com.anymore.mvpkit.widget.LoadingDialog
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by liuyuanmao on 2019/7/16.
 */
abstract class BaseFragment :Fragment(),IFragment,BaseContract.IBaseView{

    private val TAG = "BaseFragment@${hashCode()}"
    //用于存储当前Fragment的前一个可见状态
    private var mPreviousVisibleState = AtomicBoolean(false)
    protected lateinit var mLoadingDialog: LoadingDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(getLayoutRes(),container,false)
        initData(savedInstanceState)
        return rootView
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

    override fun onDestroyView() {
        hideProgressBar()
        super.onDestroyView()
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

    override fun provideLifecycleOwner()=this

    override fun showProgressBar(message: String, cancelable: Boolean) {
        if (!this::mLoadingDialog.isInitialized){
            mLoadingDialog = LoadingDialog(context!!,message,cancelable)
        }else{
            mLoadingDialog.title = message
            mLoadingDialog.setCancelable(cancelable)
        }
        mLoadingDialog.show()
    }

    override fun hideProgressBar() {
        if (this::mLoadingDialog.isInitialized && mLoadingDialog.isShowing){
            mLoadingDialog.dismiss()
        }
    }

    override fun showSuccess(stringId: Int) {
        showSuccess(getString(stringId))
    }

    override fun showSuccess(message: String) {
        toast(message)
    }

    override fun showError(stringId: Int) {
        showError(getString(stringId))
    }

    override fun showError(message: String) {
        toast(message)
    }
}