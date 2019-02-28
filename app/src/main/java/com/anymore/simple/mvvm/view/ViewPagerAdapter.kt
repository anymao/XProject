package com.anymore.simple.mvvm.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by liuyuanmao on 2019/2/28.
 */
class ViewPagerAdapter(fm:FragmentManager,private val mFragments:List<Fragment>):FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int)= mFragments[p0]

    override fun getCount()= mFragments.size
}