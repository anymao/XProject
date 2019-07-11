package com.anymore.example.mvvm.view.todo

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.anymore.example.R
import com.anymore.example.databinding.FragmentTodoBinding
import com.anymore.example.ext.toast
import com.anymore.example.mvvm.model.entry.Todo
import com.anymore.example.mvvm.view.adapter.TodosAdapter
import com.anymore.mvvmkit.mvvm.base.BindingFragment

/**
 * Created by anymore on 2019/5/10.
 */
class TodoFragment:BindingFragment<FragmentTodoBinding>() {

    companion object{
        /**
         * 未完成状态
         */
        const val STATUS_UNFINISHED = 0
        /**
         * 已完成状态
         */
        const val STATUS_DONE = 1
        private const val EXTRA_STATUS = "extra_status"

        fun instantiate(type:Int):TodoFragment{
            val bundle = Bundle()
            bundle.putInt(EXTRA_STATUS,type)
            val fragment = TodoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val mAdapter by lazy {
        TodosAdapter(context!!).apply {
            mItemEventHandler = object :TodosAdapter.OnItemEventHandler{
                override fun onClick(item: Todo) {
                    toast(item.title)
//                    TodoDetailFragment.start(context!!,item,"待办事宜")
                }
            }
        }
    }

    override fun getLayoutRes()= R.layout.fragment_todo

    override fun initData(savedInstanceState: Bundle?) {
        initRecyclerView()

    }

    private fun initRecyclerView(){
        mBinding.rvList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        mBinding.rvList.layoutManager = LinearLayoutManager(context)
        mBinding.rvList.adapter = mAdapter
    }
}