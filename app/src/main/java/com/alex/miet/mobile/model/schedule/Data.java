package com.alex.miet.mobile.model.schedule;

/**
 * This class is response holder with lesson information, includes in {@link SemesterData}
 * Contains information about room, time, week and day
 */
public class Data {

    /**
     * Information about lesson's classroom
     */
    private Room Room;

    /**
     * Information about lessons' time
     */
    private Time Time;

    /**
     * Information about lesson's discipline
     */
    private ClassModel Class;

    /**
     * number of week's lesson from 0..3 (means 1/2 numerator/denumerator)
     */
    private String DayNumber;


    /**
     * number of a lesson's day in a week 0..6
     */
    private String Day;

    /**
     * Information about lesson's group
     */
    private Group Group;

    public Room getRoom() {
        return Room;
    }

    public void setRoom(Room Room) {
        this.Room = Room;
    }

    public Time getTime() {
        return Time;
    }

    public void setTime(Time Time) {
        this.Time = Time;
    }

    public ClassModel getClassModel() {
        return Class;
    }

    public void setClassModel(ClassModel Class) {
        this.Class = Class;
    }

    public String getDayNumber() {
        return DayNumber;
    }

    public void setDayNumber(String DayNumber) {
        this.DayNumber = DayNumber;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String Day) {
        this.Day = Day;
    }

    public Group getGroup() {
        return Group;
    }

    public void setGroup(Group Group) {
        this.Group = Group;
    }
}
