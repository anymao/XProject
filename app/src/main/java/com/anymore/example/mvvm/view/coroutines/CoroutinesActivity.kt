package com.anymore.example.mvvm.view.coroutines

import android.graphics.BitmapFactory
import android.os.Bundle
import com.anymore.example.R
import com.anymore.example.databinding.ActivityCoroutinesBinding
import com.anymore.mvvmkit.mvvm.base.BindingActivity
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

/**
 * Created by liuyuanmao on 2019/5/28.
 */
class CoroutinesActivity: BindingActivity<ActivityCoroutinesBinding>(),CoroutineScope  {
    override val coroutineContext: CoroutineContext
        get() = Job()+Dispatchers.Main

    override fun initView(savedInstanceState: Bundle?)= R.layout.activity_coroutines

    override fun initData(savedInstanceState: Bundle?)= runBlocking {
        loadImage()
        loadText()
        loadJob()
        val time = measureTimeMillis {  }
    }

    private fun loadJob(){
        val job = GlobalScope.launch {
            repeat(1000){
                if (isActive)
                try {
                    Timber.e("print$it")
                    delay(500)
                }finally {
                    withContext(NonCancellable){
                        Timber.e("do in finally")
                    }
                }

            }
        }

        runBlocking {
            delay(10000)
            job.cancelAndJoin()
        }
    }

    private fun loadText() {
        val job = GlobalScope.launch {
            delay(5000)
            launch(Dispatchers.Main){
                mBinding.tvText.append(",world")
            }
        }
        mBinding.tvText.text = "hello"
//        job.join()
    }


    private fun loadImage(){
        val job = GlobalScope.launch{
            Timber.d("""current@${Thread.currentThread()}""")
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://pic37.nipic.com/20140113/8800276_184927469000_2.png")
                .build()
            val call = client.newCall(request)
            val response = call.execute()
            val bitmap = if (response.isSuccessful){
                response.body()?.let { BitmapFactory.decodeStream(it.byteStream()) }
            }else{
                null
            }
            launch(Dispatchers.Main){
                Timber.d("""current@${Thread.currentThread()}""")
                mBinding.ivImage.setImageBitmap(bitmap)
            }
        }
    }

    private fun loadImage2(){
        val deferred = GlobalScope.async{
            Timber.d("""current@${Thread.currentThread()}""")
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://pic37.nipic.com/20140113/8800276_184927469000_2.png")
                .build()
            val call = client.newCall(request)
            val response = call.execute()
            val bitmap = if (response.isSuccessful){
                response.body()?.let { BitmapFactory.decodeStream(it.byteStream()) }
            }else{
                null
            }
            bitmap
        }


        runBlocking {
            mBinding.ivImage.setImageBitmap(deferred.await())
        }
    }
}