package com.anymore.example.mvvm.model.db

import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.anymore.example.mvvm.model.db.entry.Todo

/**
 * Created by liuyuanmao on 2019/5/16.
 */
@Dao
interface TodoDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todos: List<Todo>)

    @Query("SELECT * FROM Todo WHERE userId = :userId AND type = :type")
    fun load(userId:String,type:Int):DataSource.Factory<Int,Todo>

    @Delete
    fun delete(todo: Todo)

    @Delete
    fun delete(todos: List<Todo>)
}