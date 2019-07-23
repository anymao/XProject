package com.anymore.mvpkit.mvp.lifecycle.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import timber.log.Timber

/**
 * Created by liuyuanmao on 2019/2/23.
 */
interface IFragmentLifecycle {
    /**
     * 代理 [android.support.v4.app.Fragment.onAttach]
     *
     * @param context Context
     */
    fun onAttach(context: Context)

    /**
     * 代理 [android.support.v4.app.Fragment.onCreate]
     *
     * @param savedInstanceState 数据恢复
     */
    fun onCreate(savedInstanceState: Bundle?){}

    /**
     * 代理 [android.support.v4.app.Fragment.onViewCreated]
     *
     * @param view               View
     * @param savedInstanceState 数据恢复
     */
    fun onCreateView(view: View, savedInstanceState: Bundle?){}

    /**
     * 代理 [android.support.v4.app.Fragment.onActivityCreated]
     *
     * @param savedInstanceState 数据恢复
     */
    fun onActivityCreate(savedInstanceState: Bundle?)

    /**
     * 代理 [android.support.v4.app.Fragment.onStart]
     */
    fun onStart(){}

    /**
     * 代理 [android.support.v4.app.Fragment.onResume]
     */
    fun onResume(){Timber.d("IFragmentLifecycle onResume")}

    /**
     * 代理 [android.support.v4.app.Fragment.onPause]
     */
    fun onPause(){Timber.d("IFragmentLifecycle onPause")}

    /**
     * 代理 [android.support.v4.app.Fragment.onStop]
     */
    fun onStop(){}

    /**
     * 代理 [android.support.v4.app.Fragment.onSaveInstanceState]
     *
     * @param outState 数据保存
     */
    fun onSaveInstanceState(outState: Bundle){}

    /**
     * 代理 [android.support.v4.app.Fragment.onDestroyView]
     */
    fun onDestroyView(){}

    /**
     * 代理 [android.support.v4.app.Fragment.onDestroy]
     */
    fun onDestroy(){}

    /**
     * 代理 [android.support.v4.app.Fragment.onDetach]
     */
    fun onDetach()

    /**
     * Fragment 是否添加到 Activity
     *
     * @return true if the fragment is currently added to its activity.
     */
    fun isAdded(): Boolean
}