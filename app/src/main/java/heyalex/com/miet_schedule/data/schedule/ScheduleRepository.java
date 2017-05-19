package heyalex.com.miet_schedule.data.schedule;

import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.data.BaseRepository;

/**
 * Created by mac on 13.04.17.
 */

public interface ScheduleRepository extends BaseRepository<ScheduleModel> {

    ScheduleModel getGroupByName(String groupName);

    void replaceByGroupName(String groupName, ScheduleModel model);

    void deleteByGroupName(String groupName);
}
