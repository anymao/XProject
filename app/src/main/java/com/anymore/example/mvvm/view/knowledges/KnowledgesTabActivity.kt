package com.anymore.example.mvvm.view.knowledges

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.anymore.example.R
import com.anymore.example.databinding.ActivityKnowledgesTabBinding
import com.anymore.example.ext.toast
import com.anymore.example.mvvm.model.entry.Knowledge
import com.anymore.example.mvvm.view.adapter.FragmentItem
import com.anymore.example.mvvm.view.adapter.FragmentsAdapter
import com.anymore.example.utils.UiUtils
import com.anymore.mvvmkit.mvvm.base.BindingActivity

/**
 * Created by liuyuanmao on 2019/4/30.
 */
class KnowledgesTabActivity : BindingActivity<ActivityKnowledgesTabBinding>() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        fun start(context: Context, knowledge: Knowledge) {
            val intent = Intent(context, KnowledgesTabActivity::class.java)
            intent.putExtra(EXTRA_DATA, knowledge)
            context.startActivity(intent)
        }
    }

    override fun initView(savedInstanceState: Bundle?) = R.layout.activity_knowledges_tab

    override fun initData(savedInstanceState: Bundle?) {
        UiUtils.setupToolbar(this, mBinding.toolbar)
        val knowledge = intent.getSerializableExtra(EXTRA_DATA) as Knowledge?
        if (knowledge != null) {
            setUpViewPagers(knowledge)
        } else {
            toast("传递参数时候出错!", Toast.LENGTH_LONG)
            finish()
        }

    }

    private fun setUpViewPagers(knowledge: Knowledge) {
        val fragments = ArrayList<FragmentItem>()
        for (i in knowledge.children) {
            if (i.visible == 1) {
                fragments.add(FragmentItem(KnowledgesArticlesFragment.newInstance(i.id), i.name))
            }
        }
        mBinding.viewPager.adapter = FragmentsAdapter(supportFragmentManager, fragments)
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
        title = knowledge.name
    }

    override fun useFragment() = true

}