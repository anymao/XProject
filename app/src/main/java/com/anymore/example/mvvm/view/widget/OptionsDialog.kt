package com.anymore.example.mvvm.view.widget

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.annotation.StyleRes
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import com.anymore.example.R
import com.anymore.example.databinding.DialogOptionsBinding
import com.anymore.example.mvvm.view.adapter.OptionsAdapter

/**
 * Created by liuyuanmao on 2019/5/7.
 */
class OptionsDialog(context: Context, @StyleRes themeResId:Int = R.style.downInDownOutDialogStyle)
    :AlertDialog(context,themeResId){

    private lateinit var mBinding: DialogOptionsBinding
    private val mAdapter by lazy { OptionsAdapter(context) }
    private val mOptionItems = ArrayList<OptionItem>()
    private val mAutoCloseItemClickListener by lazy { AutoCloseItemClickListener() }
    init {
        mOptionItems.add(OptionItem(0,getString(R.string.collected),R.drawable.ic_collect_0,0))
        mOptionItems.add(OptionItem(1,getString(R.string.refresh),R.drawable.ic_refresh,0))
        mOptionItems.add(OptionItem(2,getString(R.string.copy_link),R.drawable.ic_link,0))
        mOptionItems.add(OptionItem(3,getString(R.string.adjust_font),R.drawable.ic_font,0))
        mAdapter.setData(mOptionItems)
        mAdapter.mItemEventHandler = mAutoCloseItemClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_options,null,false)
        setContentView(mBinding.root)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        window?.run {
            setGravity(Gravity.BOTTOM)
            val attr = attributes
            attr.width = WindowManager.LayoutParams.MATCH_PARENT
            attr.height = WindowManager.LayoutParams.WRAP_CONTENT
            attributes = attr
        }
        initRecyclerView()
    }

    fun setOnItemClickListener(listener:OptionsAdapter.OnItemEventHandler){
        mAutoCloseItemClickListener.realOnClickListener = listener
    }

    private fun initRecyclerView() {
        mBinding.rvList.layoutManager = GridLayoutManager(context,4)
        mBinding.rvList.adapter = mAdapter
    }

    private fun getString(@StringRes id:Int)=context.getString(id)

    data class OptionItem(
        val id:Int,
        val title:String,
        @DrawableRes val icon:Int,
        val status:Int
    )

    private inner class AutoCloseItemClickListener:OptionsAdapter.OnItemEventHandler{

        internal var realOnClickListener:OptionsAdapter.OnItemEventHandler?=null

        override fun onClick(item: OptionItem) {
            realOnClickListener?.onClick(item)
            dismiss()
        }

    }

}

