package com.anymore.example.mvvm.view.todo

import android.content.Context
import android.os.Bundle
import com.anymore.example.R
import com.anymore.example.databinding.FragmentTodoDetailBinding
import com.anymore.example.mvvm.model.entry.Todo
import com.anymore.example.mvvm.view.FragmentActivity
import com.anymore.example.mvvm.viewmodel.TodoDetailFragmentViewModel
import com.anymore.mvvmkit.mvvm.base.BaseFragment

/**
 * Created by liuyuanmao on 2019/5/21.
 */
class TodoDetailFragment : BaseFragment<FragmentTodoDetailBinding, TodoDetailFragmentViewModel>() {

    companion object {
        private const val EXTRA_TODO = "EXTRA_TODO"
        fun newInstance(todo: Todo): TodoDetailFragment {
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_TODO, todo)
            val fragment = TodoDetailFragment()
            fragment.arguments = bundle
            return fragment
        }

        fun start(context: Context, todo: Todo, title: String = "") {
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_TODO, todo)
            FragmentActivity.start(context, TodoDetailFragment::class.java.name, title, bundle)
        }
    }

    private lateinit var mTodo: Todo

    override fun getLayoutRes() = R.layout.fragment_todo_detail

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mTodo = (arguments?.getSerializable(EXTRA_TODO) as Todo?) ?: Todo()
        mBinding.data = mTodo
    }
}