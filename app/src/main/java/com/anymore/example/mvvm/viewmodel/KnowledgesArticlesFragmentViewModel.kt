package com.anymore.example.mvvm.viewmodel

import android.app.Application
import com.anymore.example.mvvm.model.MainModel
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.model.paging.article.ArticlesRepository
import com.anymore.example.mvvm.model.paging.Listing
import com.anymore.mvvmkit.di.scope.FragmentScope
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/4/30.
 */


@FragmentScope
class KnowledgesArticlesFragmentViewModel @Inject constructor(private val mModel:MainModel,application: Application):BaseViewModel(application){

    private val repository by lazy { ArticlesRepository(application) }


    fun getKnowledgesArticlesListing(cid:Int):Listing<Article>{
        return repository.getKnowledgesArticlesListing(cid)
    }
}