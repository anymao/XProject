package com.anymore.mvvmkit.log

import android.text.TextUtils
import android.util.Log
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException


/**
 * Created by liuyuanmao on 2019/4/11.
 */
class FileLogTree(private val dir:String): Timber.Tree() {

    private val sdf by lazy { SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault()) }
    private val mWriterExecutor by lazy { Executors.newSingleThreadExecutor() }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        //排除没有TAG的日志
        if (TextUtils.isEmpty(tag)){
            return
        }
        mWriterExecutor.execute { writeLog(priority,tag!!,message) }
    }


    private fun writeLog(priority: Int,tag: String,message: String){
        val dir = File(dir)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val file = File("$dir/log.txt")
        val writer: FileWriter
        var bufferedWriter: BufferedWriter? = null
        try {
            if (!file.exists()) {
                file.createNewFile()
            }
            writer = FileWriter(file, true)
            bufferedWriter = BufferedWriter(writer)
            bufferedWriter.write(getFormatLog(priority,tag, message))
            bufferedWriter.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                bufferedWriter?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    private fun getFormatLog(priority: Int,tag: String,message: String):String{
        val level = when(priority){
            Log.VERBOSE->"V"
            Log.DEBUG->"D"
            Log.INFO->"I"
            Log.WARN->"W"
            Log.ERROR->"E"
            Log.ASSERT->"A"
            else ->"V"
        }
        return String.format("%s\t/%s/%s  %s",sdf.format(Date()),level,tag,message)
    }

}