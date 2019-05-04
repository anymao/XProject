package com.anymore.example.mvvm.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.anymore.example.mvvm.model.db.entry.UserInfo

/**
 * Created by anymore on 2019/4/20.
 */
@Database(entities = [UserInfo::class],version = 1)
abstract class AppDatabase: RoomDatabase(){
    companion object{
        const val DB_NAME = "WanAndroid.db"
    }
    abstract fun userInfoDao():UserInfoDao
}