package com.anymore.example.mvvm.model

import android.app.Application
import com.anymore.mvvmkit.mvvm.base.BaseModel
import javax.inject.Inject

/**
 * Created by anymore on 2019/5/10.
 */
class TodoModel @Inject constructor(application: Application)
    :BaseModel(application){
//    private val mDao by lazy { mRepositoryComponent.getRepository().obtainRoomDatabase() }
}