package heyalex.com.miet_schedule.schedule_widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

import javax.inject.Inject;

import heyalex.com.miet_schedule.data.lessons.LessonsRepository;

/**
 * Created by alexf on 20.05.2017.
 */

public class LessonRemoteService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return(new LessonsViewsFactory(this.getApplicationContext(),
                intent));
    }
}
