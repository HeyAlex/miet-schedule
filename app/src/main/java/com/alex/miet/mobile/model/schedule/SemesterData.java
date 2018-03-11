package com.alex.miet.mobile.model.schedule;

/**
 * POJO that includes all lessons, name of semester, and schedule times of studies
 */
public class SemesterData {

    /**
     * All lessons for current group
     */
    private Data[] Data;

    /**
     * Name of semester
     */
    private String Semester;

    /**
     * Schedule times of studies
     */
    private Times[] Times;

    public Data[] getData() {
        return Data;
    }

    public void setData(Data[] Data) {
        this.Data = Data;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String Semestr) {
        this.Semester = Semestr;
    }

    public Times[] getTimes() {
        return Times;
    }

    public void setTimes(Times[] Times) {
        this.Times = Times;
    }


}
