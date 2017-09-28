package heyalex.com.ggreendao_generator;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.ToMany;

import java.io.File;

public class Generator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "heyalex.com.miet_schedule");
        addNews(schema);
        addSchedule(schema);
        File dir = new File("../some_works/app/src/main/java-gen");
        dir.mkdirs();
        new DaoGenerator().generateAll(schema, dir.getAbsolutePath());
    }

    public static void addNews(Schema schema) {
        Entity news = schema.addEntity("NewsModel");
        news.addStringProperty("title");
        news.addStringProperty("link");
        news.addStringProperty("description");
        news.addStringProperty("imageUrl");
        news.addStringProperty("date");
    }

    public static void addSchedule(Schema schema) {
        Entity schedule = schema.addEntity("ScheduleModel");
        schedule.addStringProperty("group").primaryKey();
        schedule.addStringProperty("semestr");

        Entity lesson = schema.addEntity("LessonModel");
        lesson.addIdProperty().autoincrement().primaryKey();
        lesson.addIntProperty("week");
        lesson.addIntProperty("day");
        lesson.addStringProperty("room");
        lesson.addIntProperty("time");
        lesson.addStringProperty("timeFull");
        lesson.addStringProperty("timeFrom");
        lesson.addStringProperty("timeTo");
        lesson.addStringProperty("teacher");
        lesson.addStringProperty("teacherFull");
        lesson.addStringProperty("disciplineName");
        lesson.addStringProperty("disciplineType");
        lesson.addStringProperty("code");

        Property lessonProperty = lesson.addStringProperty("groupName").notNull().getProperty();
        ToMany groupToLessons = schedule.addToMany(lesson, lessonProperty);
        groupToLessons.setName("lessons");

        //lesson.implementsSerializable();
        //schedule.implementsSerializable();
    }

}