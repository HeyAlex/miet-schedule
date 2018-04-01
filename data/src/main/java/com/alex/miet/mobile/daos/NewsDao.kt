package com.alex.miet.mobile.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.alex.miet.mobile.entities.NewsItem
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
abstract class NewsDao : BaseDao<NewsItem> {
    @Query("DELETE FROM news")
    abstract fun deleteAll()

    @Query("SELECT * FROM news")
    abstract fun loadAll(): Maybe<List<NewsItem>>
}