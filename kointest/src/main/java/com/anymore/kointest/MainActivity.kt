package com.anymore.kointest

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.anymore.kointest.di.HelloRepositoryImpl
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mViewModel:MainActivityViewModel by viewModel()
    private val helloRepository:HelloRepositoryImpl by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity",helloRepository?.toString())
        lifecycle.addObserver(mViewModel)
        mViewModel.mHello.observe(this, Observer { Toast.makeText(this,"$it + anymore",Toast.LENGTH_LONG).show() })
    }
}
