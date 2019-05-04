package com.anymore.example.mvvm.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragmentsAdapter(fm:FragmentManager, private val fragments:List<FragmentItem>): FragmentPagerAdapter(fm){

    override fun getItem(position: Int) = fragments[position].fragment

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments[position].title
    }

}

data class FragmentItem(val fragment:Fragment,val title:String="")