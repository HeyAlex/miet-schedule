package com.alex.miet.mobile.schedule.model;

import com.alex.miet.mobile.entities.LessonItem;

public class LessonAdapterModel extends TypeAdapterModel {

    private LessonItem lesson;

    public LessonItem getLesson() {
        return lesson;
    }

    public void setLesson(LessonItem lesson) {
        this.lesson = lesson;
    }

    @Override
    protected ViewType setViewType() {
        return ViewType.LESSON;
    }
}
