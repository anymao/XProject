package com.anymore.example.mvvm.model.db.entry


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.support.annotation.NonNull
import com.anymore.example.mvvm.model.db.converters.UserInfoConverter

/**
 * Created by anymore on 2019/4/20.
 */
@Entity
@TypeConverters(UserInfoConverter::class)
data class UserInfo(

    @NonNull
    @PrimaryKey
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