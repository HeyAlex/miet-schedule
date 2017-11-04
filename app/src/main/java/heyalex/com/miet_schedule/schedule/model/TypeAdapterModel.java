package heyalex.com.miet_schedule.schedule.model;

public abstract class TypeAdapterModel {

    protected abstract ViewType setViewType();

    public enum ViewType {
        HEADER,
        LESSON
    }
}
