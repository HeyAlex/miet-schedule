package com.hey.mietunoff.mietunofficial.schedule.model;

import com.hey.mietunoff.mietunofficial.LessonModel;

public class LessonAdapterModel extends TypeAdapterModel {

    private LessonModel lesson;

    public LessonModel getLesson() {
        return lesson;
    }

    public void setLesson(LessonModel lesson) {
        this.lesson = lesson;
    }

    @Override
    protected ViewType setViewType() {
        return ViewType.LESSON;
    }
}
