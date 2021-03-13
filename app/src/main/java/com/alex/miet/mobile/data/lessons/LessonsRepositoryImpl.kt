package com.alex.miet.mobile.data.lessons

import java.util.Collections
import com.alex.miet.mobile.daos.LessonDao
import com.alex.miet.mobile.entities.LessonItem
import com.alex.miet.mobile.model.schedule.ScheduleComparator
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Specific [LessonsRepository] implementation
 */
class LessonsRepositoryImpl (private val dao: LessonDao) : LessonsRepository {

    override fun save(entity: LessonItem) {
        Single.fromCallable {
            dao.insert(entity)
        }
    }

    override fun saveAll(entities: List<LessonItem>) {
        Single.fromCallable {
            dao.insert(entities)
        }
    }

    override fun getAll(): Maybe<List<LessonItem>> {
        return dao.loadAll()
    }

    override fun update(entity: LessonItem) {
        Single.fromCallable {
            dao.update(entity)
        }
    }

    override fun updateAll(entities: List<LessonItem>) {
        Single.fromCallable {
            dao.update(entities)
        }
    }

    override fun delete(entity: LessonItem) {
        Single.fromCallable {
            dao.delete(entity)
        }
    }

    override fun deleteAll() {
        dao.deleteAll()
    }

    override fun deleteAllByGroupName(groupName: String) {
            val lessonsByGroup = dao.loadByGroupName(groupName).blockingGet()
            dao.delete(lessonsByGroup)
    }

    override fun replaceAllByGroupName(groupName: String, lessons: List<LessonItem>) {
            val lessonsByGroup = dao.loadByGroupName(groupName).blockingGet()
        if(lessonsByGroup != null) {
            dao.delete(lessonsByGroup)
        }
        dao.insert(lessons)
    }

    override fun getLessonsByWeekAndDay(groupName: String, week: Int, day: Int): Maybe<List<LessonItem>> {
        return dao.loadByWeekAndDay(groupName, week, day)
    }

    override fun getLessonsForWeek(groupName: String, week: Int): Maybe<List<LessonItem>> {
        return dao.loadByWeek(groupName, week)
    }

    override fun getLessonsForGroup(groupName: String): Maybe<List<LessonItem>> {
        return dao.loadByGroupName(groupName)
    }
}
