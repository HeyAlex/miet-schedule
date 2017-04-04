package heyalex.com.miet_schedule.model.schedule;

/**
 * Created by alexf on 22.09.2016.
 */
public class Data {
    private Room Room;

    private Time Time;

    private ClassModel Class;

    private String DayNumber;

    private String Day;

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
