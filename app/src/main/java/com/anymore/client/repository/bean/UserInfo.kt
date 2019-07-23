package com.anymore.client.repository.bean

data class UserInfo(
    val id: Int,
    val username: String,
    val password: String,
    val token: String,
    val type: Int,
    val email: String,
    val icon: String,
    val chapterTops: List<String>,
    val collectIds: List<String>,
    var online:Boolean = false
)