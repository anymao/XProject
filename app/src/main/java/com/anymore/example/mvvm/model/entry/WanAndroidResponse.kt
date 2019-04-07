package com.anymore.example.mvvm.model.entry

/**
 * Created by liuyuanmao on 2019/3/12.
 */
/**
 * base response
 */
data class WanAndroidResponse<D> (
    val errorCode: Int,
    val errorMsg: String?,
    val data: D?)

class UserInfo {

    /**
     * chapterTops : []
     * collectIds : []
     * email :
     * icon :
     * id : 20933
     * password :
     * token :
     * type : 0
     * username : anymore0503
     */

    var email: String? = null
    var icon: String? = null
    var id: Int = 0
    var password: String? = null
    var token: String? = null
    var type: Int = 0
    var username: String? = null
    private var chapterTops: List<String>? = null
    var collectIds: List<String>? = null

    fun getChapterTops(): List<*>? {
        return chapterTops
    }

    fun setChapterTops(chapterTops: List<String>) {
        this.chapterTops = chapterTops
    }
}

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

data class HomeArticle(
    val apkLink: String,
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
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
)

data class ArticleTag(
    val name: String,
    val url: String
)

data class HomeArticles(
    val curPage: Int,
    val datas: List<HomeArticle>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

