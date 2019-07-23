package com.anymore.mvpkit.widget

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * Created by liuyuanmao on 2019/7/16.
 */
object ToastUtils {

    private var mToast:Toast?=null

    fun show(context: Context,@StringRes stringId:Int,duration: Int = Toast.LENGTH_SHORT){
        show(context,context.getString(stringId),duration)
    }

    fun show(context: Context,message:CharSequence?,duration: Int = Toast.LENGTH_SHORT){
        if (message.isNullOrEmpty()){
            return
        }
        if (mToast == null){
            mToast = Toast.makeText(context,message,duration)
        }else{
            mToast!!.setText(message)
            mToast!!.duration = duration
        }
        mToast!!.show()
    }
}