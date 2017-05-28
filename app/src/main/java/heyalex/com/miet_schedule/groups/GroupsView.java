package heyalex.com.miet_schedule.groups;

import java.util.List;

import heyalex.com.miet_schedule.ScheduleModel;

/**
 * An interface for {@link GroupsFragment} and
 * {@link heyalex.com.miet_schedule.schedule_widget.ScheduleAppWidgetConfigureActivity}
 */
public interface GroupsView {

    /**
     * Show hint if no groups added by user
     */
    void showHint();

    /**
     * Show groups
     *
     * @param groups that user already downloaded
     */
    void showGroups(List<ScheduleModel> groups);
}
