package com.alex.miet.mobile.data.news

import com.alex.miet.mobile.daos.NewsDao
import com.alex.miet.mobile.entities.NewsItem
import io.reactivex.Maybe

import io.reactivex.Single

/**
 * Specific [NewsRepository] implementation
 */
class NewsRepositoryImpl(private val dao: NewsDao) : NewsRepository {

    override fun save(entity: NewsItem) {
        Single.fromCallable {
            dao.insert(entity)
        }
    }

    override fun saveAll(entities: List<NewsItem>) {
        Single.fromCallable {
            dao.insert(entities)
        }
    }

    override fun getAll(): Maybe<List<NewsItem>> {
        return dao.loadAll()
    }

    override fun update(entity: NewsItem) {
        Single.fromCallable {
            dao.update(entity)
        }
    }

    override fun updateAll(entities: List<NewsItem>) {
        Single.fromCallable {
            dao.update(entities)
        }
    }

    override fun delete(entity: NewsItem) {
        Single.fromCallable {
            dao.delete(entity)
        }
    }

    override fun deleteAll() {
        Single.fromCallable {
            dao.deleteAll()
        }
    }
}
