package com.anymore.example.mvvm.view.web

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

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

}
