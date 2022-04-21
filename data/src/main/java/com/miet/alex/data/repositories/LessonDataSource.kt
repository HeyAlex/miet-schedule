package com.miet.alex.data.repositories

import com.alex.miet.miet_api.MietApiService
import com.miet.alex.data.entities.LessonItem
import com.miet.alex.data.mappers.LessonMapper
import javax.inject.Inject

class LessonDataSource @Inject constructor(
    private val mietApiService: MietApiService,
    private val lessonMapper: LessonMapper
) {
    suspend fun getLessons(groupName: String): Pair<String, List<LessonItem>> =
        lessonMapper.map(mietApiService.schedule(groupName))
}