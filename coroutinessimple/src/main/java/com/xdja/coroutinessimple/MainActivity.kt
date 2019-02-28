package com.xdja.coroutinessimple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        let {  }
    }


    private fun getData():List<String>{
        val result = ArrayList<String>()
        for (i in 1 .. 23){
            result.add("item${i+1}")
        }
        return  result
    }
}
