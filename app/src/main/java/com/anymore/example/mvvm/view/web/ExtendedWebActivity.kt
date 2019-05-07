package com.anymore.example.mvvm.view.web

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IntRange
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.anymore.example.R
import com.anymore.example.mvvm.view.adapter.OptionsAdapter
import com.anymore.example.mvvm.view.widget.OptionsDialog
import com.anymore.example.mvvm.viewmodel.ExtendedWebActivityViewModel
import com.anymore.mvvmkit.mvvm.ViewModelFactory
import javax.inject.Inject

/**
 * 扩展功能的Web界面
 * Created by liuyuanmao on 2019/5/6.
 */
class ExtendedWebActivity:WebActivity(){

    companion object {
        fun start(context: Context, url: String){
            val intent = Intent(context,ExtendedWebActivity::class.java)
            intent.putExtra(EXTRA_URL,url)
            context.startActivity(intent)
        }
    }

    private lateinit var mViewModel: ExtendedWebActivityViewModel
    private val mMoreDialog by lazy { OptionsDialog(this).apply {
        setOnItemClickListener(object : OptionsAdapter.OnItemEventHandler {
            override fun onClick(item: OptionsDialog.OptionItem) {
                Toast.makeText(this@ExtendedWebActivity,item.title,Toast.LENGTH_SHORT).show()
            }
        })
    } }

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_extended_web_activity,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_more->{
                mMoreDialog.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun initData(savedInstanceState: Bundle?) {
        mViewModel = ViewModelProviders.of(this,mViewModelFactory).get(ExtendedWebActivityViewModel::class.java)
        lifecycle.addObserver(mViewModel)
        super.initData(savedInstanceState)
        mViewModel.mToast.observe(this, Observer { Toast.makeText(this,it,Toast.LENGTH_LONG).show() })
    }


    override fun onDestroy() {
        lifecycle.removeObserver(mViewModel)
        super.onDestroy()
    }

    override fun injectable()=true

    override fun getUrl(): String {
        return super.getUrl()
    }

    private fun adjustFont(){

        mAgentWeb.webCreator.webView.settings.textZoom
    }

}
