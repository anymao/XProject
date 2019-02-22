package com.anymore.mvvmkit.mvvm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.util.ArrayMap
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

//class ViewModelFactory @Inject constructor(private val creators:Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory{
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        //通过class找到相应ViewModel的Provider
//        val creator = creators[modelClass]?:creators.entries.firstOrNull{
//            modelClass.isAssignableFrom(it.key)
//        }?.value?:throw IllegalArgumentException("unknown model class $modelClass")
//        try {
//            @Suppress("UNCHECKED_CAST")
//            return creator.get() as T //通过get()方法获取到ViewModel
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//    }
//}

class ViewModelFactory @Inject
constructor(/*private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>*/) : ViewModelProvider.Factory {
    private val creators: Map<Class<out ViewModel>, Provider<ViewModel>> = ArrayMap()
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("unknown model class $modelClass")
        }
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            Timber.e(e)
            throw e
        }
    }
}