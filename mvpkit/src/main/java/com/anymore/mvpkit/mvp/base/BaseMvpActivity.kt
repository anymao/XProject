package com.anymore.mvpkit.mvp.base

import android.os.Bundle
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/7/16.
 */
abstract class BaseMvpActivity<P:BaseContract.IBasePresenter> :BaseActivity(){

    @Inject
    protected lateinit var mPresenter:P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(mPresenter)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mPresenter)
    }

    override fun injectable()=true
}