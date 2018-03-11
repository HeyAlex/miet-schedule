package com.alex.miet.mobile.data.lessons;

import com.alex.miet.mobile.data.BaseRepository;

import java.util.List;

import com.alex.miet.mobile.LessonModel;

/**
 * An interface for lesson repository
 */
public interface LessonsRepository extends BaseRepository<LessonModel> {

    /**
     * Delete all lessons by group name
     *
     * @param groupName associated with lessons
     */
    void deleteAllByGroupName(String groupName);

    /**
     * Replace all lessons by group name
     *
     * @param groupName associated with lessons
     * @param lessons   List of LessonModel to replace
     */
    void replaceAllByGroupName(String groupName, Iterable<LessonModel> lessons);

    /**
     * Get lessons for specific day
     *
     * @param groupName associated with lessons
     * @param week      associated with lessons
     * @param day       associated with lessons
     * @return empty list if no lessons, else returns list of lessons for a specific day
     */
    List<LessonModel> getLessonsByWeekAndDay(String groupName, int week, int day);

    /**
     * Get lessons for specific week
     *
     * @param groupName associated with lessons
     * @param week      associated with lessons
     * @return empty list if no lessons, else returns list of lessons for a specific week
     */
    List<LessonModel> getLessonsForWeek(String groupName, int week);
}
