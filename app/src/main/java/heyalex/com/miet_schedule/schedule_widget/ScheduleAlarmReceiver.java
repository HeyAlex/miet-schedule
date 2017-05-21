package heyalex.com.miet_schedule.schedule_widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import timber.log.Timber;

/**
 * Created by alexf on 21.05.2017.
 */

public class ScheduleAlarmReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);
        String group = intent.getStringExtra("group");
        startWakefulService(context, ScheduleUpdateService.getScheduleIntent(context,
                ScheduleUpdateService.TODAY_ACTION + String.valueOf(widgetId), widgetId, group));
        Timber.i("start service");
        ScheduleUpdateService.setupAlarm(context , widgetId , group);
    }
}