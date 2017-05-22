package heyalex.com.miet_schedule.schedule_widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by alexf on 20.05.2017.
 */

public class LessonRemoteService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new LessonsViewsFactory(this.getApplicationContext(),
                intent));
    }
}
