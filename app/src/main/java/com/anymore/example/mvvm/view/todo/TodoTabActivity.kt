package com.anymore.example.mvvm.view.todo

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.anymore.example.R
import com.anymore.example.databinding.ActivityTodosBinding
import com.anymore.example.mvvm.view.adapter.FragmentItem
import com.anymore.example.mvvm.view.adapter.FragmentsAdapter
import com.anymore.example.utils.UiUtils
import com.anymore.mvvmkit.mvvm.base.BindingActivity

/**
 * Created by anymore on 2019/5/10.
 */
class TodoTabActivity : BindingActivity<ActivityTodosBinding>() {

    private lateinit var mFragments: List<FragmentItem>
    override fun initView(savedInstanceState: Bundle?) = R.layout.activity_todos

    override fun initData(savedInstanceState: Bundle?) {
        UiUtils.setupToolbar(this, mBinding.toolbar)
        initViewPager()
    }

    private fun initViewPager() {
        title = getString(R.string.unfinished)
        mFragments = listOf(
            FragmentItem(TodoFragment.instantiate(TodoFragment.TYPE_UNFINISHED), getString(R.string.unfinished)),
            FragmentItem(TodoFragment.instantiate(TodoFragment.TYPE_DONE), getString(R.string.done))
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
                        mBinding.bnv.selectedItemId = R.id.type_unfinished
                        mBinding.toolbar.title = getString(R.string.unfinished)
                    }
                    1 -> {
                        mBinding.bnv.selectedItemId = R.id.type_done
                        mBinding.toolbar.title = getString(R.string.done)
                    }
                }
            }
        })

        mBinding.bnv.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.type_unfinished -> mBinding.viewPager.currentItem = 0
                R.id.type_done -> mBinding.viewPager.currentItem = 1
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun useFragment() = true
}