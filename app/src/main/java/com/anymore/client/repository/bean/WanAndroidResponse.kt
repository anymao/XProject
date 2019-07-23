package com.anymore.client.repository.bean

data class WanAndroidResponse<D> (
    val errorCode: Int,
    val errorMsg: String?,
    val data: D?)