package com.anymore.example.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.anymore.example.mvvm.model.TodoModel
import com.anymore.example.mvvm.model.entry.Todo
import com.anymore.example.mvvm.view.todo.PagedListing
import com.anymore.example.mvvm.view.todo.TodoFragment
import com.anymore.mvvmkit.di.scope.FragmentScope
import com.anymore.mvvmkit.mvvm.SingleLiveEvent
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by anymore on 2019/5/10.
 */
@FragmentScope
class TodoFragmentViewModel @Inject constructor(application: Application, private val mModel: TodoModel) :
    BaseViewModel(application) {
    private var currentPage: Int = 1
    private var isLoadAll: Boolean = false
    var todoType: Int = TodoFragment.TYPE_UNFINISHED
    val pageData = MutableLiveData<PagedListing<Todo>>()
    val toast = SingleLiveEvent<String>()


    fun refresh(){
        loadPage(1,true,false)
    }

    fun loadMore(){
        loadPage(currentPage+1,false)
    }


    private fun loadPage(page:Int,refresh:Boolean = false,loadMore:Boolean=true) {
        if (!isLoadAll) {
            val disposable = mModel.getTodoList(page, todoType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = {
                    currentPage = it.curPage
                    isLoadAll = it.pageCount <= it.curPage
                    val listing = PagedListing(it.datas,refresh,loadMore,isLoadAll)
                    pageData.value = listing
                }, onError = {
                    Timber.e(it)
                    toast.value = it.message
                    val listing:PagedListing<Todo> = PagedListing(emptyList(),refresh,loadMore,isLoadAll,false)
                    pageData.value = listing
                })
            addToCompositeDisposable(disposable)

        }
    }


}