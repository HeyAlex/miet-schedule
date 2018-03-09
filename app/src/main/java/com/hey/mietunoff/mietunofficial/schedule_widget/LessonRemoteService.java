package com.hey.mietunoff.mietunofficial.schedule_widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Adapter remote service
 * Used in {@link ScheduleUpdateService} for lessons adapter
 */
public class LessonRemoteService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new LessonsViewsFactory(this.getApplicationContext(),
                intent));
    }
}
