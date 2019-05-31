package com.anymore.example.mvvm.view.web

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebSettings
import android.widget.Toast
import com.anymore.example.R
import com.anymore.example.app.config.AppConfig
import com.anymore.example.ext.toast
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.view.adapter.OptionsAdapter
import com.anymore.example.mvvm.view.widget.FontSizeSelectBar
import com.anymore.example.mvvm.view.widget.FontSizeSelectDialog
import com.anymore.example.mvvm.view.widget.OptionsDialog
import com.anymore.example.mvvm.viewmodel.ExtendedWebActivityViewModel
import com.anymore.mvvmkit.mvvm.ViewModelFactory
import javax.inject.Inject

/**
 * 扩展功能的Web界面
 * Created by liuyuanmao on 2019/5/6.
 */
class ExtendedWebActivity : WebActivity() {

    companion object {
        private const val EXTRA_ARTICLE = "extra_article"
        const val FONT_SMALL = 0
        const val FONT_NORMAL = 1
        const val FONT_BIG = 2
        const val FONT_EXTRA_BIG = 3

        fun start(context: Context, url: String) {
            val intent = Intent(context, ExtendedWebActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            context.startActivity(intent)
        }

        fun start(context: Context, article: Article) {
            val intent = Intent(context, ExtendedWebActivity::class.java)
            intent.putExtra(EXTRA_ARTICLE, article)
            context.startActivity(intent)
        }
    }

    private lateinit var mViewModel: ExtendedWebActivityViewModel
    private var mArticle: Article? = null
    private val mOptionsDialog by lazy {
        OptionsDialog(this).apply {
            setOnItemClickListener(object : OptionsAdapter.OnItemEventHandler {
                override fun onClick(item: OptionsDialog.OptionItem) {
                    when (item.id) {
                        0 -> {
                            mArticle?.let { mViewModel.collectAticle(it) }
                        }
                        1 -> {
                            refresh()
                        }
                        2 -> {
                            copyLink()
                        }
                        3 -> {
                            mFontSizeDialog.show()
                        }
                    }
                }
            })
        }
    }

    private val mFontSizeDialog by lazy {
        FontSizeSelectDialog(this).apply {
            setOnRatingChangedListener(object : FontSizeSelectBar.OnRatingChangedListener {
                override fun onRatingChanged(rating: Int, textSize: Int) {
                    mViewModel.saveWebViewTextSize(rating)
                }
            })
        }
    }

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_extended_web_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_more -> {
                mOptionsDialog.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun initData(savedInstanceState: Bundle?) {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ExtendedWebActivityViewModel::class.java)
        lifecycle.addObserver(mViewModel)
        super.initData(savedInstanceState)
        mViewModel.webViewTextSize.observe(this, Observer {
            it?.run {
                mFontSizeDialog.setRating(this)
                adjustFont(this)
            }
        })
        mViewModel.mToast.observe(this, Observer { toast(it, Toast.LENGTH_LONG) })
    }


    override fun onDestroy() {
        lifecycle.removeObserver(mViewModel)
        super.onDestroy()
    }

    override fun injectable() = true

    override fun getUrl(): String {
        mArticle = intent.getSerializableExtra(EXTRA_ARTICLE) as Article?
        return mArticle?.link ?: super.getUrl()
    }

    private fun refresh() {
        mAgentWeb.urlLoader.reload()
    }

    private fun copyLink() {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.primaryClip = ClipData.newPlainText(null, mAgentWeb.webCreator.webView.url)
        toast("复制成功!")
    }

    private fun adjustFont(size: Int) {
        AppConfig.saveWebViewTextSize(size)
        with(mAgentWeb.webCreator.webView.settings) {
            when (size) {
                FONT_SMALL -> textZoom = 75
                FONT_NORMAL -> textZoom = 100
                FONT_BIG -> textZoom = 125
                FONT_EXTRA_BIG -> textZoom = 150
            }
            useWideViewPort = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
            loadWithOverviewMode = true
        }
    }


}
