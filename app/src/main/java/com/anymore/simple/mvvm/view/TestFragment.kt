package com.anymore.simple.mvvm.view

import android.os.Bundle
import android.widget.Toast
import com.anymore.mvvmkit.mvvm.base.BindingFragment
import com.anymore.simple.R
import com.anymore.simple.databinding.FragmentTestBinding

class TestFragment:BindingFragment<FragmentTestBinding>() {
    override fun getLayoutRes()= R.layout.fragment_test

    override fun initData(savedInstanceState: Bundle?) {
        mBinding.btnTest.setOnClickListener {
            Toast.makeText(context,"TestFragment",Toast.LENGTH_LONG).show()
        }
    }
}