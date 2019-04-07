package com.anymore.example.mvvm.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragmentsAdapter(fm:FragmentManager, private val fragments:List<Fragment>): FragmentPagerAdapter(fm){

    override fun getItem(p0: Int) = fragments[p0]

    override fun getCount() = fragments.size

}