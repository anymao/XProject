package com.anymore.client.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import com.anymore.client.R
import com.anymore.client.utils.UiUtils
import com.anymore.mvpkit.mvp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_fragment.*
import timber.log.Timber
import kotlin.reflect.KClass

/**
 * 容纳单个Fragment的布局Activity
 * Created by liuyuanmao on 2019/5/7.
 */
class FragmentActivity : BaseActivity() {

    companion object {
        private const val EXTRA_TITLE = "extra_title"
        private const val EXTRA_FRAGMENT_NAME = "extra_fragment_name"
        private const val EXTRA_FRAGMENT_BUNDLE = "extra_fragment_bundle"

        /**
         * 利用本[FragmentActivity]来启动一个Fragment
         */
        fun start(context: Context, fragmentName: String, title: String = "", bundle: Bundle? = null) {
            val intent = Intent(context, FragmentActivity::class.java)
            intent.putExtra(EXTRA_FRAGMENT_NAME, fragmentName)
            intent.putExtra(EXTRA_TITLE, title)
            intent.putExtra(EXTRA_FRAGMENT_BUNDLE, bundle)
            context.startActivity(intent)
        }

        fun start(context: Context, fragmentClass: KClass<Fragment>, title: String = "", bundle: Bundle? = null) {
            start(context,fragmentClass.java.name,title,bundle)
        }
    }

    private var mFragment: Fragment? = null

    override fun initView(savedInstanceState: Bundle?) = R.layout.activity_fragment

    override fun initData(savedInstanceState: Bundle?) {
        UiUtils.setupToolbar(this, toolbar)
        title = getFragmentTitle()
        mFragment = instantiateFragment()
        mFragment?.apply {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, this)
                .commit()
        }
    }

    override fun useFragment() = true

    private fun <F : Fragment> instantiateFragment(): F? {
        val fragmentName = intent.getStringExtra(EXTRA_FRAGMENT_NAME)
        val bundle = intent.getBundleExtra(EXTRA_FRAGMENT_BUNDLE)
        var fragment: F? = null
        if (!TextUtils.isEmpty(fragmentName)) {
            try {
                @Suppress("UNCHECKED_CAST")
                fragment = Fragment.instantiate(this, fragmentName, bundle) as F
            } catch (e: Exception) {
                Timber.e(e, "Start A FragmentActivity failed!")
            }
        }
        return fragment
    }

    private fun getFragmentTitle() = intent.getStringExtra(EXTRA_TITLE)

}