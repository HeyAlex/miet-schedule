package heyalex.com.miet_schedule.model.schedule;

/**
 * Contains information about schedule time exercise
 * Part of {@link SemesterData}
 */
/*package*/ class Times {

    /**
     * Number of exercise in all schedule time
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
