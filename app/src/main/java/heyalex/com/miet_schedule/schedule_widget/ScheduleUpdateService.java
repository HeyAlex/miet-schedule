package heyalex.com.miet_schedule.schedule_widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.transition.Visibility;
import android.view.View;
import android.widget.RemoteViews;

import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;

import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.util.DateMietHelper;

/**
 * Created by alexf on 13.04.2017.
 */

public class ScheduleUpdateService extends IntentService {

    public static final String tommorow = "TOMMOROW";
    public static final String today = "TODAY";

    @Inject
    LessonsRepository lessonsRepository;


    public ScheduleUpdateService() {
        super("ScheduleUpdateService");
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
        if(intent != null){
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);
            String group = intent.getStringExtra("group");

            if (intent.getAction() != null){
                if(intent.getAction().startsWith(tommorow)){
                    RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.schedule_app_widget);
                    remoteViews.setInt(R.id.widget_control, "setBackgroundResource", R.drawable.chevron_left);
                    remoteViews.setTextViewText(R.id.header,group);
                    remoteViews.setOnClickPendingIntent(R.id.widget_control, getPingPendingIntent(this, today + String.valueOf(widgetId),widgetId,group));
                    remoteViews.setTextViewText(R.id.day,"ЗАВТРА");

                    if(lessonsRepository.getLessonsByWeekAndDay( group, 2, DateMietHelper.getDayInWeek(DateTime.now().plusDays(3))).isEmpty()){
                        remoteViews.setViewVisibility(R.id.no_schedule_view, View.VISIBLE);
                        remoteViews.setViewVisibility(R.id.lessons, View.INVISIBLE);
                        appWidgetManager.updateAppWidget(widgetId, remoteViews);
                        appWidgetManager.notifyAppWidgetViewDataChanged(widgetId,
                                R.id.lessons);
                    }else {
                        remoteViews.setViewVisibility(R.id.no_schedule_view, View.INVISIBLE);
                        remoteViews.setViewVisibility(R.id.lessons, View.VISIBLE);
                        Intent adapter = new Intent(this, LessonRemoteService.class);
                        adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                        adapter.putExtra("group", group);
                        adapter.putExtra("day", DateMietHelper.getDayInWeek(DateTime.now().plusDays(3)));
                        Uri data = Uri.parse(adapter.toUri(Intent.URI_INTENT_SCHEME));
                        adapter.setData(data);
                        remoteViews.setRemoteAdapter(R.id.lessons, adapter);
                        appWidgetManager.updateAppWidget(widgetId, remoteViews);
                        appWidgetManager.notifyAppWidgetViewDataChanged(widgetId,
                                R.id.lessons);
                    }

                } else if(intent.getAction().startsWith(today)){
                    RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.schedule_app_widget);
                    remoteViews.setTextViewText(R.id.day,"СЕГОДНЯ");
                    remoteViews.setInt(R.id.widget_control, "setBackgroundResource", R.drawable.chevron_right);
                    remoteViews.setTextViewText(R.id.header,group);
                    remoteViews.setOnClickPendingIntent(R.id.widget_control, getPingPendingIntent(this, tommorow+ String.valueOf(widgetId),widgetId,group));

                    if(lessonsRepository.getLessonsByWeekAndDay( group, 2, DateMietHelper.getDayInWeek(DateTime.now().plusDays(2))).isEmpty()){
                        remoteViews.setViewVisibility(R.id.no_schedule_view, View.VISIBLE);
                        remoteViews.setViewVisibility(R.id.lessons, View.INVISIBLE);
                        appWidgetManager.updateAppWidget(widgetId, remoteViews);
                        appWidgetManager.notifyAppWidgetViewDataChanged(widgetId,
                                R.id.lessons);
                    }else {
                        remoteViews.setViewVisibility(R.id.no_schedule_view, View.INVISIBLE);
                        remoteViews.setViewVisibility(R.id.lessons, View.VISIBLE);
                        Intent adapter = new Intent(this, LessonRemoteService.class);
                        adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                        adapter.putExtra("group", group);
                        adapter.putExtra("day", DateMietHelper.getDayInWeek(DateTime.now().plusDays(2)));
                        Uri data = Uri.parse(adapter.toUri(Intent.URI_INTENT_SCHEME));
                        adapter.setData(data);
                        remoteViews.setRemoteAdapter(R.id.lessons, adapter);
                        appWidgetManager.updateAppWidget(widgetId, remoteViews);
                        appWidgetManager.notifyAppWidgetViewDataChanged(widgetId,
                                R.id.lessons);
                    }
                }
            }
        }
    }
    public static PendingIntent getPingPendingIntent(Context context, String action, int widgetId, String group) {
        Intent resultValue = new Intent(context, ScheduleUpdateService.class);
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        resultValue.putExtra("group", group);
        resultValue.setAction(action);
        return PendingIntent.getService(context, 0, resultValue, 0);
    }
}
