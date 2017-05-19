package heyalex.com.miet_schedule.schedule_widget;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;

/**
 * Created by alexf on 13.04.2017.
 */

public class ScheduleUpdateService extends IntentService {

    @Inject
    LessonsRepository lessonsRepository;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ScheduleUpdateService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ScheduleApp.get(this)
                .getApplicationComponent()
                .inject(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
