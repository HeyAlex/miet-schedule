package heyalex.com.miet_schedule.data.schedule;

import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.data.BaseRepository;

/**
 * An interface for schedule repository
 */
public interface ScheduleRepository extends BaseRepository<ScheduleModel> {

    /**
     * Get ScheduleModel by group name
     *
     * @param groupName that associated with schedule
     * @return {@link ScheduleModel} that contains all information about schedule for {@param groupName}
     */
    ScheduleModel getGroupByName(String groupName);

    /**
     * Replace ScheduleModel by group name
     *
     * @param groupName that associated with schedule
     * @param model     ScheduleModel to replace
     */
    void replaceByGroupName(String groupName, ScheduleModel model);

    /**
     * Delete ScheduleModel by group name
     *
     * @param groupName that associated with schedule
     */
    void deleteByGroupName(String groupName);

    boolean isGroupCached(String groupName);
}
