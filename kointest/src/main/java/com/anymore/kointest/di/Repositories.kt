package com.anymore.kointest.di

/**
 * Created by liuyuanmao on 2019/2/25.
 */
interface HelloRepository{
    fun sayHello():String
}

class HelloRepositoryImpl:HelloRepository{
    override fun sayHello()="hello,"

}