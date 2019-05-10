package com.anymore.example.mvvm.view.widget

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.StyleRes
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import com.anymore.example.R
import com.anymore.mvvmkit.dp2px

/**
 * Created by liuyuanmao on 2019/5/7.
 */
class FontSizeSelectDialog(context: Context, @StyleRes themeResId:Int = R.style.downInDownOutDialogStyle)
    :AlertDialog(context,themeResId){
    private val mParent by lazy { FrameLayout(context) }
    val mBar by lazy { FontSizeSelectBar(context) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val param = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,context.dp2px(120))
        mBar.setPadding(context.dp2px(50),0,context.dp2px(50),context.dp2px(40))
        mParent.setBackgroundColor(Color.WHITE)
        mParent.addView(mBar,param)
        setContentView(mParent)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        window?.run {
            setGravity(Gravity.BOTTOM)
            val attr = attributes
            attr.width = WindowManager.LayoutParams.MATCH_PARENT
            attr.height = WindowManager.LayoutParams.WRAP_CONTENT
            attributes = attr
        }
    }

    fun setOnRatingChangedListener(listener: FontSizeSelectBar.OnRatingChangedListener){
        mBar.setOnRatingChangedListener(listener)
    }

    fun setRating(rating:Int){
        mBar.setRating(rating)
    }
}