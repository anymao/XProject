package com.anymore.example.mvvm.view.todo

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.anymore.example.R
import com.anymore.example.databinding.FragmentTodoBinding
import com.anymore.example.ext.toast
import com.anymore.example.mvvm.model.entry.Todo
import com.anymore.example.mvvm.view.adapter.TodosAdapter
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
        TodosAdapter(context!!).apply {
            mItemEventHandler = object :TodosAdapter.OnItemEventHandler{
                override fun onClick(item: Todo) {
                    toast(item.title)
                    TodoDetailFragment.start(context!!,item,"待办事宜")
                }
            }
        }
    }

    override fun getLayoutRes()= R.layout.fragment_todo

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        initRecyclerView()
        mViewModel.todoType = arguments?.getInt(EXTRA_TYPE, TYPE_UNFINISHED)?: TYPE_UNFINISHED
        mViewModel.toast.observe(this, Observer { toast(it) })
        mViewModel.pageData.observe(this, Observer {
            it?.apply {
                if (success){
                    if (isRefresh){
                        mAdapter.setData(list)
                        if (!hasMore){
                            mBinding.srlRefresh.finishRefreshWithNoMoreData()
                        }else{
                            mBinding.srlRefresh.finishRefresh(true)
                        }
                    }else{
                        mAdapter.addData(list)
                        if (!hasMore){
                            mBinding.srlRefresh.finishLoadMoreWithNoMoreData()
                        }else{
                            mBinding.srlRefresh.finishLoadMore(true)
                        }
                    }
                }else{
                    if (isRefresh){
                        mBinding.srlRefresh.finishRefresh(false)
                    }else{
                        mBinding.srlRefresh.finishLoadMore(false)
                    }
                }
            }

        })
        mBinding.srlRefresh.setOnRefreshListener {
            mViewModel.refresh()
        }
        mBinding.srlRefresh.setOnLoadMoreListener{
            mViewModel.loadMore()
        }
        mBinding.srlRefresh.autoRefresh()
    }

    private fun initRecyclerView(){
        mBinding.rvList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        mBinding.rvList.layoutManager = LinearLayoutManager(context)
        mBinding.rvList.adapter = mAdapter
    }
}