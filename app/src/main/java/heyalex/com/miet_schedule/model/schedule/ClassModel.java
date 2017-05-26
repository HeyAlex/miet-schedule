package heyalex.com.miet_schedule.model.schedule;

/**
 * This class is response holder with discipline information, includes in {@link Data}
 * Contains information about discipline name and teacher that teach that discipline
 */
public class ClassModel {

    /**
     * Discipline name
     */
    private String Name;

    /**
     * Full teacher name
     */
    private String TeacherFull;

    /**
     * Short teacher name
     */
    private String Teacher;

    private String Code;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTeacherFull() {
        return TeacherFull;
    }

    public void setTeacherFull(String TeacherFull) {
        this.TeacherFull = TeacherFull;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String Teacher) {
        this.Teacher = Teacher;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }
}
