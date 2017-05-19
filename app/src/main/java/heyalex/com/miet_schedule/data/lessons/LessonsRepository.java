package heyalex.com.miet_schedule.data.lessons;

import java.util.List;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.data.BaseRepository;

/**
 * Created by mac on 09.05.17.
 */

public interface LessonsRepository extends BaseRepository<LessonModel> {

    void deleteAllByGroupName(String groupName);

    void replaceAllByGroupName(String groupName, Iterable<LessonModel> lessons);

    List<LessonModel> getLessonsByWeekAndDay(String groupName, int week, int day);

    List<LessonModel> getLessonsForWeek(String groupName, int week);
}
