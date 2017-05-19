package heyalex.com.miet_schedule.schedule;

import heyalex.com.miet_schedule.mvp.BasePresenter;

/**
 * Created by alexf on 07.04.2017.
 */

/*package*/ interface SchedulePresenter extends BasePresenter<ScheduleView> {

    void getCachedScheduleForGroup(String groupName);

    void updateScheduleForGroup(String groupName);
}
