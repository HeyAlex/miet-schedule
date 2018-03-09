package com.hey.mietunoff.mietunofficial.model.schedule;

/**
 * Contains information about schedule time exercise
 * Part of {@link Data}
 */
public class Time {

    /**
     * String of representation of number of exercise in all schedule time
     */
    private String Time;

    /**
     * Lesson time start
     */
    private String TimeFrom;

    /**
     * Lesson time end
     */
    private String TimeTo;

    /**
     * number of exercise in all schedule time
     */
    private String Code;

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getTimeFrom() {
        return TimeFrom;
    }

    public void setTimeFrom(String TimeFrom) {
        this.TimeFrom = TimeFrom;
    }

    public String getTimeTo() {
        return TimeTo;
    }

    public void setTimeTo(String TimeTo) {
        this.TimeTo = TimeTo;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }
}
