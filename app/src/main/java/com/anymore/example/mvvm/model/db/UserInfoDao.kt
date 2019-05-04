package com.anymore.example.mvvm.model.db

import android.arch.persistence.room.*
import com.anymore.example.mvvm.model.db.entry.UserInfo
import io.reactivex.Single

/**
 * Created by anymore on 2019/4/20.
 */

@Dao
interface UserInfoDao {
    @Query("SELECT * FROM UserInfo WHERE online ")
    fun getCurrentUser():Single<UserInfo>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(userInfo: UserInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userInfo: UserInfo)

    @Delete
    fun delete(userInfo: UserInfo)

    @Query("UPDATE UserInfo SET online=:newStatus WHERE online")
    fun updateOnlineStatus(newStatus:Boolean)
}