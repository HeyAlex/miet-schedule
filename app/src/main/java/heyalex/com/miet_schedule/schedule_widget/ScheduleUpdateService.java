package heyalex.com.miet_schedule.schedule_widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
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

    private static final String tommorow = "TOMMOROW";


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
                if(intent.getAction().equals(tommorow)){
                    RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.schedule_app_widget);
                    remoteViews.setTextViewText(R.id.header,group + "(завтра)");
                    Intent adapter = new Intent(this, LessonRemoteService.class);
                    adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                    adapter.putExtra("group", group);
                    adapter.putExtra("day", DateMietHelper.getDayInWeek(DateTime.now().plusDays(4)));
                    Uri data = Uri.parse(adapter.toUri(Intent.URI_INTENT_SCHEME));
                    adapter.setData(data);
                    remoteViews.setRemoteAdapter(R.id.lessons, adapter);
                    appWidgetManager.updateAppWidget(widgetId, remoteViews);
                    appWidgetManager.notifyAppWidgetViewDataChanged(widgetId,
                            R.id.lessons);
                }


            }else {
                RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.schedule_app_widget);
                remoteViews.setTextViewText(R.id.header,group+ "(сегодня)");
                remoteViews.setOnClickPendingIntent(R.id.header, getPingPendingIntent(this, tommorow,widgetId,group));
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
