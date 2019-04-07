package com.anymore.mvvmkit.repository

import android.support.annotation.IntDef
import android.support.annotation.Nullable


/**
 * Created by liuyuanmao on 2019/2/28.
 */
/**
 * 数据分为本地数据源和远程数据源
 * 泛型S指代本地数据源Database或者是HttpClient
 */
class Source<out S>(@Code val code:Int, @Nullable val data:S?, val message:String? = null){

    companion object {
        const val SUCCESS = 0
        const val FAILED = -1
        const val DOING = 1

        fun<S> createSuccess(data:S):Source<S>{
            return Source(SUCCESS,data,"成功!")
        }

        fun<S> createFailed(message:String?=null):Source<S>{
            return Source(FAILED,null,message)
        }
    }

    @IntDef(SUCCESS, FAILED, DOING)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Code
}