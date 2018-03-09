package com.hey.mietunoff.mietunofficial.schedule.model;

public abstract class TypeAdapterModel {

    protected abstract ViewType setViewType();

    public enum ViewType {
        HEADER,
        LESSON
    }
}
