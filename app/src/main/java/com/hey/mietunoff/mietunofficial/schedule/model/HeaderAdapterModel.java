package com.hey.mietunoff.mietunofficial.schedule.model;

public class HeaderAdapterModel extends TypeAdapterModel {

    private String header;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    protected ViewType setViewType() {
        return ViewType.HEADER;
    }
}
