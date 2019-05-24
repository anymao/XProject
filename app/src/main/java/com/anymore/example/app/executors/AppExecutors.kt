package com.anymore.example.app.executors

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by anymore on 2019/5/20.
 */
object AppExecutors{
    val diskIoExecutor :Executor by lazy { Executors.newFixedThreadPool(12) }
    val networkExecutor:Executor by lazy { Executors.newFixedThreadPool(12) }
    val mainExecutor:Executor by lazy {
        object :Executor{
            private val mainHandler by lazy { Handler(Looper.getMainLooper()) }
            override fun execute(command: Runnable?) {
                command?.let { mainHandler.post(it) }
            }
        }
    }
}
