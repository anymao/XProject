package com.anymore.example.mvvm.model.exception

import java.lang.RuntimeException

/**
 * 后台数据接口异常类
 */
class WanAndroidException : RuntimeException {
    constructor() : super()

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(cause: Throwable) : super(cause)

}