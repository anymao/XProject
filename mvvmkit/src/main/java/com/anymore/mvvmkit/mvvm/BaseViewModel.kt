package com.anymore.mvvmkit.mvvm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.anymore.mvvmkit.mvvm.lifecycle.IViewModel

/**
 * Created by liuyuanmao on 2019/2/20.
 */
open class BaseViewModel(application: Application):AndroidViewModel(application),IViewModel
