package heyalex.com.miet_schedule.schedule.model;

import heyalex.com.miet_schedule.LessonModel;

public class LessonAdapterModel extends TypeAdapterModel {

    public LessonModel getLesson() {
        return lesson;
    }

    public void setLesson(LessonModel lesson) {
        this.lesson = lesson;
    }

    private LessonModel lesson;

    @Override
    protected ViewType setViewType() {
        return ViewType.LESSON;
    }
}
