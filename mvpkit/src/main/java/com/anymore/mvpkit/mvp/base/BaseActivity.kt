package com.anymore.mvpkit.mvp.base

import android.support.v7.app.AppCompatActivity
import com.anymore.mvpkit.exts.toast
import com.anymore.mvpkit.mvp.lifecycle.activity.IActivity
import com.anymore.mvpkit.widget.LoadingDialog

/**
 * BaseActivity 实现[BaseContract.IBaseView]接口，后续MVP在Activity作为View层时候
 * 如无必需，无需重复实现其接口
 * Created by liuyuanmao on 2019/7/16.
 */
abstract class BaseActivity : AppCompatActivity(),IActivity,BaseContract.IBaseView{

    protected lateinit var mLoadingDialog: LoadingDialog


    override fun onDestroy() {
        hideProgressBar()
        super.onDestroy()
    }

    override fun provideLifecycleOwner()=this

    override fun showProgressBar(message: String, cancelable: Boolean) {
        if (!this::mLoadingDialog.isInitialized){
            mLoadingDialog = LoadingDialog(this,message,cancelable)
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
