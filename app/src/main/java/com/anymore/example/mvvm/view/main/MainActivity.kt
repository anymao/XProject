package com.anymore.example.mvvm.view.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.anymore.example.R
import com.anymore.example.databinding.ActivityMainBinding
import com.anymore.example.mvvm.view.adapter.FragmentsAdapter
import com.anymore.example.mvvm.viewmodel.MainActivityViewModel
import com.anymore.mvvmkit.mvvm.base.BaseActivity

/**
 * Created by liuyuanmao on 2019/3/11.
 */
class MainActivity:BaseActivity<ActivityMainBinding, MainActivityViewModel>(){

    private lateinit var mFragments:List<Fragment>

    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_main

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        mFragments = listOf(HomeFragment(),
                            DiscoveryFragment(),
                            MyFragment())
        val fragmentAdapter = FragmentsAdapter(supportFragmentManager,mFragments)
        mBinding.viewPager.adapter = fragmentAdapter
        mBinding.viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position :Int, positionOffset :Float, positionOffsetPixels :Int) {

            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0->{
                        mBinding.bnv.selectedItemId = R.id.action_home
                        mBinding.toolbar.title = getString(R.string.home)
                    }
                    1->{
                        mBinding.bnv.selectedItemId = R.id.action_discovery
                        mBinding.toolbar.title = getString(R.string.discovery)
                    }
                    2->{
                        mBinding.bnv.selectedItemId = R.id.action_my
                        mBinding.toolbar.title = getString(R.string.my)
                    }
                }
            }
        })

        mBinding.bnv.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.action_home-> mBinding.viewPager.currentItem = 0
                R.id.action_discovery->mBinding.viewPager.currentItem = 1
                R.id.action_my->mBinding.viewPager.currentItem = 2
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun useFragment() = true
}