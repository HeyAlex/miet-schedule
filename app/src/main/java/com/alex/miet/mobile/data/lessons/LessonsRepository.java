package com.alex.miet.mobile.data.lessons;

import com.alex.miet.mobile.data.BaseRepository;

import java.util.List;

import com.alex.miet.mobile.entities.LessonItem;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * An interface for lesson repository
 */
public interface LessonsRepository extends BaseRepository<LessonItem> {

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
     * @param lessons   List of LessonItem to replace
     */
    void replaceAllByGroupName(String groupName, List<LessonItem> lessons);

    /**
     * Get lessons for specific day
     *
     * @param groupName associated with lessons
     * @param week      associated with lessons
     * @param day       associated with lessons
     * @return empty list if no lessons, else returns list of lessons for a specific day
     */
    Maybe<List<LessonItem>> getLessonsByWeekAndDay(String groupName, int week, int day);

    /**
     * Get lessons for specific week
     *
     * @param groupName associated with lessons
     * @param week      associated with lessons
     * @return empty list if no lessons, else returns list of lessons for a specific week
     */
    Maybe<List<LessonItem>> getLessonsForWeek(String groupName, int week);

    Maybe<List<LessonItem>> getLessonsForGroup(String groupName);
}
