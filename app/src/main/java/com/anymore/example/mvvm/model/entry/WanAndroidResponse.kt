package com.anymore.example.mvvm.model.entry

/**
 * Created by liuyuanmao on 2019/3/12.
 */
/**
 * base response
 */
class WanAndroidResponse<D> {
    var errorCode: Int = 0
    var errorMsg: String? = null
    var data: D? = null
}
class Article {
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

