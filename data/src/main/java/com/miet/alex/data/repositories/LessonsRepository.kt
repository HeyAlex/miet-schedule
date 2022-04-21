package com.miet.alex.data.repositories

import com.miet.alex.data.entities.GroupItem
import com.miet.alex.data.entities.LessonItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LessonsRepository @Inject constructor(
    private val lessonStore: LocalLessonDataSource,
    private val groupsStore: LocalGroupDataSource,
    private val lessonDataSource: LessonDataSource
) {
    suspend fun loadLessons(groupName: String) {
        lessonDataSource.getLessons(groupName).also {
            val groupItem = groupsStore.getGroup(groupName)

            if (groupItem != null) {
                val updateGroup = groupItem.copy(
                    id = groupItem.id,
                    group = groupItem.group,
                    semester = it.first
                )
                groupsStore.update(updateGroup)
            } else {
                groupsStore.insert(GroupItem(group = groupName, semester = it.first))
            }

            lessonStore.save(it.second)
        }
    }

    suspend fun observeLessons(groupName: String): Flow<List<LessonItem>> {
        return lessonStore.getLessonsForGroup(groupName)
    }
}