package com.anymore.example.mvvm.model.db.entry

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.support.annotation.NonNull
import java.io.Serializable

import com.anymore.example.BR

/**
 * Created by liuyuanmao on 2019/5/13.
 */

@Entity
class Todo:BaseObservable(),Serializable {

    @PrimaryKey
    @NonNull
    var id: Int = 0

    var completeDate: Long= 0L
        set(value) {
            field = value
            notifyPropertyChanged(BR.completeDate)
        }
        @Bindable get

    var completeDateStr: String?= null
        set(value) {
            field = value
            notifyPropertyChanged(BR.completeDateStr)
        }
        @Bindable get

    var content: String?= null
        set(value) {
            field = value
            notifyPropertyChanged(BR.content)
        }
        @Bindable get

    var date: Long= 0L
        set(value) {
            field = value
            notifyPropertyChanged(BR.date)
        }
        @Bindable get

    var dateStr: String?= null
        set(value) {
            field = value
            notifyPropertyChanged(BR.dateStr)
        }
        @Bindable get

    var priority: Int= 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.priority)
        }
        @Bindable get

    var status: Int= 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.status)
        }
        @Bindable get

    var title: String?= null
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }
        @Bindable get

    var type: Int= 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.type)
        }
        @Bindable get

    var userId: Int= 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.userId)
        }
        @Bindable get

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Todo

        if (completeDate != other.completeDate) return false
        if (completeDateStr != other.completeDateStr) return false
        if (content != other.content) return false
        if (date != other.date) return false
        if (dateStr != other.dateStr) return false
        if (id != other.id) return false
        if (priority != other.priority) return false
        if (status != other.status) return false
        if (title != other.title) return false
        if (type != other.type) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = completeDate.hashCode()
        result = 31 * result + (completeDateStr?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + date.hashCode()
        result = 31 * result + (dateStr?.hashCode() ?: 0)
        result = 31 * result + id
        result = 31 * result + priority
        result = 31 * result + status
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + type
        result = 31 * result + userId
        return result
    }
}