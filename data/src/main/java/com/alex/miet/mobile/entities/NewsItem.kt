package com.alex.miet.mobile.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ColumnInfo
import org.joda.time.DateTime


@Entity(tableName = "news")
data class NewsItem(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long? = null,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "date") val date: String,
        @ColumnInfo(name = "link") val link: String,
        @ColumnInfo(name = "imageUrl") val imageUrl: String,
        @ColumnInfo(name = "description") val description: String)