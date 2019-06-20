//package com.anymore.example.mvvm.view.adapter
//
//import android.support.v7.util.DiffUtil
//import android.view.ViewGroup
//import com.anymore.example.mvvm.model.entry.Article
//import com.anymore.example.mvvm.view.adapter.viewholder.BindingViewHolder
//
///**
// * Created by liuyuanmao on 2019/6/20.
// */
//class ArticlesAdapter(callback: DiffUtil.ItemCallback<Article> = diffCallback) : NetStatusPagingAdapter<Article>(callback) {
//    override fun getItemViewLayout(position: Int): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BindingViewHolder {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun onBindViewHolder(p0: BindingViewHolder, p1: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//
//    companion object {
//        private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
//
//            override fun areItemsTheSame(oldItem: Article, newItem: Article) = (oldItem.id == newItem.id)
//
//            override fun areContentsTheSame(oldItem: Article, newItem: Article) = (oldItem.id == newItem.id)
//        }
//    }
//}