package com.alex.miet.mobile.data.news

import com.alex.miet.mobile.daos.NewsDao
import com.alex.miet.mobile.entities.NewsItem
import io.reactivex.Maybe

/**
 * Specific [NewsRepository] implementation
 */
class NewsRepositoryImpl(private val dao: NewsDao) : NewsRepository {

    override fun save(entity: NewsItem) {
        dao.insert(entity)
    }

    override fun saveAll(entities: List<NewsItem>) {
        dao.insert(entities)
    }

    override fun getAll(): Maybe<List<NewsItem>> {
        return dao.loadAll()
    }

    override fun update(entity: NewsItem) {
        dao.update(entity)
    }

    override fun updateAll(entities: List<NewsItem>) {
        dao.update(entities)
    }

    override fun delete(entity: NewsItem) {
        dao.delete(entity)
    }

    override fun deleteAll() {
        dao.deleteAll()
    }
}
