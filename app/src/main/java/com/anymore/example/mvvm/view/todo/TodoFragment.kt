package com.anymore.example.mvvm.view.todo

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.anymore.example.R
import com.anymore.example.databinding.FragmentTodoBinding
import com.anymore.example.ext.toast
import com.anymore.example.mvvm.model.entry.Todo
import com.anymore.example.mvvm.view.adapter.TodosPagingAdapter
import com.anymore.example.mvvm.viewmodel.TodoFragmentViewModel
import com.anymore.mvvmkit.mvvm.base.BaseFragment

/**
 * Created by anymore on 2019/5/10.
 */
class TodoFragment:BaseFragment<FragmentTodoBinding,TodoFragmentViewModel>() {

    companion object{
        const val TYPE_UNFINISHED = 0
        const val TYPE_DONE = 1
        private const val EXTRA_TYPE = "extra_type"

        fun instantiate(type:Int):TodoFragment{
            val bundle = Bundle()
            bundle.putInt(EXTRA_TYPE,type)
            val fragment = TodoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val mAdapter by lazy {
        TodosPagingAdapter(context!!).apply {
            mItemEventHandler = object :TodosPagingAdapter.OnItemEventHandler{
                override fun onClick(item: Todo) {
                    toast(item.title)
                }
            }
        }
    }

    override fun getLayoutRes()= R.layout.fragment_todo

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        initRecyclerView()
        val type = arguments?.getInt(EXTRA_TYPE, TYPE_UNFINISHED)?: TYPE_UNFINISHED
        mViewModel.loadTodoList(type).pagedList.observe(this, Observer { mAdapter.submitList(it) })
    }

    private fun initRecyclerView(){
        mBinding.rvList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        mBinding.rvList.layoutManager = LinearLayoutManager(context)
        mBinding.rvList.adapter = mAdapter
    }
}