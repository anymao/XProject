package com.anymore.mvpkit.widget

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.anymore.mvpkit.R
import kotlinx.android.synthetic.main.mvpkit_dialog_loading.*

/**
 * Created by liuyuanmao on 2019/7/16.
 */
class LoadingDialog(context: Context, title: String? = null, cancelable: Boolean = true) :
    AlertDialog(context, R.style.mvpkit_loading_dialog) {

    var title: String? = ""
        set(value) {
            field = value
            if (field.isNullOrEmpty()) {
                tvTitle?.text = ""
            } else {
                tvTitle?.text = field
            }
        }

    init {
        this.title = title
        setCancelable(cancelable)
        setCanceledOnTouchOutside(cancelable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mvpkit_dialog_loading)
        title?.let { tvTitle.text = it }
    }


    override fun setTitle(titleId: Int) {
        title = context.getString(titleId)
    }
}
