package heyalex.com.miet_schedule.schedule_item;

import javax.inject.Inject;

import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.data.schedule.ScheduleRepository;
import heyalex.com.miet_schedule.schedule.ScheduleView;

/**
 * Created by alexf on 11.05.2017.
 */

public class ScheduleFragmentPresenterImpl implements ScheduleFragmentPresenter{

    private LessonsRepository lessonsRepository;
    private ScheduleItemView view;

    @Inject
    public ScheduleFragmentPresenterImpl(LessonsRepository lessonsRepository) {
        this.lessonsRepository = lessonsRepository;
    }

    @Override
    public void onViewAttached(ScheduleItemView view) {
        this.view = view;
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }
}
