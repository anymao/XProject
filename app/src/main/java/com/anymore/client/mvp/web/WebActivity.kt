package com.anymore.client.mvp.web

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.LinearLayout
import com.anymore.client.R
import com.anymore.client.utils.UiUtils
import com.anymore.mvpkit.mvp.base.BaseActivity
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.activity_web.*

/**
 * 实现基本web功能的Activity
 * Created by liuyuanmao on 2019/7/23.
 */
open class WebActivity :BaseActivity(){
    companion object {
        const val EXTRA_URL = "extra_url"

        fun start(context: Context, url: String){
            val intent = Intent(context,WebActivity::class.java)
            intent.putExtra(EXTRA_URL,url)
            context.startActivity(intent)
        }
    }
    protected lateinit var mAgentWeb: AgentWeb

    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_web

    override fun initData(savedInstanceState: Bundle?) {
        UiUtils.setupToolbar(this,toolbar)
        val webChromeClient = object : WebChromeClient(){
            override fun onReceivedTitle(view: WebView?, title: String?) {
                toolbar.title = title
            }
        }
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(webContainer, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setWebChromeClient(webChromeClient)
            .createAgentWeb()
            .ready()
            .go(getUrl())
        with(mAgentWeb.agentWebSettings.webSettings){
            useWideViewPort = true
            loadWithOverviewMode = true
        }
    }

    override fun onResume() {
        super.onResume()
        mAgentWeb.webLifeCycle.onResume()
    }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mAgentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    protected open fun getUrl():String?{
        return intent.getStringExtra(EXTRA_URL)?:"http://www.baidu.com"
    }
}