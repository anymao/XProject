package com.anymore.example.mvvm.model.db.converters

import android.arch.persistence.room.TypeConverter
import com.anymore.example.ext.toList
import com.google.gson.Gson

/**
 * Created by anymore on 2019/4/20.
 */
class UserInfoConverter{

    @TypeConverter
    fun stringList2Json(list: List<String>):String=Gson().toJson(list)

    @TypeConverter
    fun json2StringList(json: String)=Gson().toList<String>(json)
}
