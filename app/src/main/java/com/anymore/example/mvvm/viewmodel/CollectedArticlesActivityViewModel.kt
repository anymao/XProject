package com.anymore.example.mvvm.viewmodel

import android.app.Application
import com.anymore.example.mvvm.model.entry.Article
import com.anymore.example.mvvm.model.paging.ArticlesRepository
import com.anymore.example.mvvm.model.paging.Listing
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/5/6.
 */
@ActivityScope
class CollectedArticlesActivityViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
    private val repository by lazy { ArticlesRepository(application) }
    val listing:Listing<Article> by lazy { repository.getCollectedArticlesListing() }
}