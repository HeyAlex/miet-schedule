package heyalex.com.miet_schedule.schedule_widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;

/**
 * Created by alexf on 20.05.2017.
 */

public class LessonsViewsFactory implements RemoteViewsService.RemoteViewsFactory{

    private List<LessonModel> lessons = new  ArrayList<>();
    private Context context;
    private String group;
    private int widgetId;
    private int day;
    @Inject
    LessonsRepository lessonsRepository;

    public LessonsViewsFactory(Context context, Intent adapter) {
        this.group = adapter.getStringExtra("group");
        this.day = adapter.getIntExtra("day",0);
        this.widgetId = adapter.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        this.context = context;
    }

    @Override
    public void onCreate() {

        ScheduleApp.get(context)
                .getApplicationComponent()
                .inject(this);

        this.lessons =lessonsRepository.getLessonsByWeekAndDay( group,
                2,
                day);
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return(lessons.size());
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final LessonModel lesson = lessons.get(position);
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.lesson_item);
        remoteView.setTextViewText(R.id.itogname_schedule, lesson.getDisciplineName());
        remoteView.setTextViewText(R.id.room_schedule, lesson.getRoom());
        remoteView.setTextViewText(R.id.prep_schedule, lesson.getTeacher());
        remoteView.setTextViewText(R.id.timeTo_schedule, lesson.getTimeTo());
        remoteView.setTextViewText(R.id.timeFrom_schedule, lesson.getTimeFrom());
        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
