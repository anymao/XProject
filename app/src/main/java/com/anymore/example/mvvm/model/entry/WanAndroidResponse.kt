package com.anymore.example.mvvm.model.entry

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.anymore.example.BR
import java.io.Serializable

/**
 * Created by liuyuanmao on 2019/3/12.
 */

object ResponseCode{
    const val OK = 0

}

/**
 * base response
 */
data class WanAndroidResponse<D> (
    val errorCode: Int,
    val errorMsg: String?,
    val data: D?)

/**
 * 微信公众号文章
 */
class WxArticle {
    /**
     * children : []
     * courseId : 13
     * id : 408
     * name : 鸿洋
     * order : 190000
     * parentChapterId : 407
     * userControlSetTop : false
     * visible : 1
     */
    var courseId: Int = 0
    var id: Int = 0
    var name: String? = null
    var order: Int = 0
    var parentChapterId: Int = 0
    var isUserControlSetTop: Boolean = false
    var visible: Int = 0
    var children: List<String>? = null
}

/**
 * banner轮播
 */
data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)

/**
 * 每一条文章的信息
 */
data class Article(
    val apkLink: String,
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val projectLink: String,
    val publishTime: Long,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<ArticleTag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
): BaseObservable(),Serializable{
    var collect: Boolean = false
        set(value){
            field = value
            notifyPropertyChanged(BR.collect)
        }
        @Bindable get
}

/**
 * 文章tag
 */
data class ArticleTag(
    val name: String,
    val url: String
):Serializable

/**
 * 对请求文章列表的封装
 */
data class Articles(
    val curPage: Int,
    val datas: List<Article>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

/**
 * 请求到的所有知识体系的tag封装
 */
data class Knowledge(
    val children: List<Knowledge>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
):Serializable

class Todo:BaseObservable(),Serializable {

    var id: Int = 0

    var completeDate: Long= 0L
        set(value) {
            field = value
            notifyPropertyChanged(BR.completeDate)
        }
        @Bindable get

    var completeDateStr: String?= null
        set(value) {
            field = value
            notifyPropertyChanged(BR.completeDateStr)
        }
        @Bindable get

    var content: String?= null
        set(value) {
            field = value
            notifyPropertyChanged(BR.content)
        }
        @Bindable get

    var date: Long= 0L
        set(value) {
            field = value
            notifyPropertyChanged(BR.date)
        }
        @Bindable get

    var dateStr: String?= null
        set(value) {
            field = value
            notifyPropertyChanged(BR.dateStr)
        }
        @Bindable get

    var priority: Int= 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.priority)
        }
        @Bindable get

    var status: Int= 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.status)
        }
        @Bindable get

    var title: String?= null
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }
        @Bindable get

    var type: Int= 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.type)
        }
        @Bindable get

    var userId: Int= 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.userId)
        }
        @Bindable get

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as com.anymore.example.mvvm.model.db.entry.Todo

        if (completeDate != other.completeDate) return false
        if (completeDateStr != other.completeDateStr) return false
        if (content != other.content) return false
        if (date != other.date) return false
        if (dateStr != other.dateStr) return false
        if (id != other.id) return false
        if (priority != other.priority) return false
        if (status != other.status) return false
        if (title != other.title) return false
        if (type != other.type) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = completeDate.hashCode()
        result = 31 * result + (completeDateStr?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + date.hashCode()
        result = 31 * result + (dateStr?.hashCode() ?: 0)
        result = 31 * result + id
        result = 31 * result + priority
        result = 31 * result + status
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + type
        result = 31 * result + userId
        return result
    }
}

data class Todos(
    val curPage: Int,
    val datas: List<Todo>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)
