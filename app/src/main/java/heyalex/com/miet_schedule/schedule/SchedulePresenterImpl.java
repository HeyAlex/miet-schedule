package heyalex.com.miet_schedule.schedule;

import javax.inject.Inject;

import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;

/**
 * Created by mac on 10.05.17.
 */

public class SchedulePresenterImpl implements SchedulePresenter{

    private ScheduleRepository scheduleRepository;
    private LessonsRepository lessonsRepository;
    private ScheduleView view;

    @Inject
    public SchedulePresenterImpl(ScheduleRepository scheduleRepository,
                                 LessonsRepository lessonsRepository) {
        this.scheduleRepository = scheduleRepository;
        this.lessonsRepository = lessonsRepository;
    }

    @Override
    public void onViewAttached(ScheduleView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void getCachedScheduleForGroup(String groupName) {

    }
}
