package com.anymore.example.mvvm.view.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.anymore.example.R
import com.anymore.example.databinding.ActivityMainBinding
import com.anymore.example.mvvm.view.adapter.FragmentItem
import com.anymore.example.mvvm.view.adapter.FragmentsAdapter
import com.anymore.example.mvvm.viewmodel.MainActivityViewModel
import com.anymore.mvvmkit.mvvm.base.BaseActivity
import timber.log.Timber

/**
 * Created by liuyuanmao on 2019/3/11.
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    private lateinit var mFragments: List<FragmentItem>

    override fun initView(savedInstanceState: Bundle?) = R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        mFragments = listOf(
            FragmentItem(HomeFragment(), getString(R.string.home)),
            FragmentItem(DiscoveryFragment(), getString(R.string.discovery)),
            FragmentItem(MyFragment(), getString(R.string.my))
        )
        val fragmentAdapter = FragmentsAdapter(supportFragmentManager, mFragments)
        mBinding.viewPager.adapter = fragmentAdapter
        mBinding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        mBinding.bnv.selectedItemId = R.id.action_home
                    }
                    1 -> {
                        mBinding.bnv.selectedItemId = R.id.action_discovery
                    }
                    2 -> {
                        mBinding.bnv.selectedItemId = R.id.action_my
                    }
                }
            }
        })

        mBinding.bnv.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> mBinding.viewPager.currentItem = 0
                R.id.action_discovery -> mBinding.viewPager.currentItem = 1
                R.id.action_my -> mBinding.viewPager.currentItem = 2
            }
            return@setOnNavigationItemSelectedListener true
        }

        for (i in 0..50){
            Timber.tag("lym").d("message:$i")
        }
    }

    override fun useFragment() = true
}