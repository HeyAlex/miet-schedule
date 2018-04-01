package com.alex.miet.mobile.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "group_table")
data class GroupItem(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "group_name") val group: String,
        @ColumnInfo(name = "semester") val semester: String? = null)