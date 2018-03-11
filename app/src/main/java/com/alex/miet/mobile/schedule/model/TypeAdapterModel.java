package com.alex.miet.mobile.schedule.model;

public abstract class TypeAdapterModel {

    protected abstract ViewType setViewType();

    public enum ViewType {
        HEADER,
        LESSON
    }
}
