package heyalex.com.miet_schedule.groups;

import java.util.List;

import heyalex.com.miet_schedule.ScheduleModel;

/**
 * Created by mac on 09.05.17.
 */

public interface GroupsView {
    void showHint();

    void showGroups(List<ScheduleModel> groups);
}
