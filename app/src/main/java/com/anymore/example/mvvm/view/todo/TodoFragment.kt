package com.anymore.example.mvvm.view.todo

import android.os.Bundle
import com.anymore.example.R
import com.anymore.example.databinding.FragmentTodoBinding
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

    override fun getLayoutRes()= R.layout.fragment_todo

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
    }
}