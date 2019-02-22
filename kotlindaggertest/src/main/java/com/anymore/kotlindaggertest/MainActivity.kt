package com.anymore.kotlindaggertest

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mViewModelFactory:ViewModelProvider.Factory
    private lateinit var mViewModel:MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this,mViewModelFactory).get(MainActivityViewModel::class.java)
        lifecycle.addObserver(mViewModel)
        mViewModel.getMessage().observe(this, Observer { Toast.makeText(this,it,Toast.LENGTH_SHORT).show() })
    }
}
