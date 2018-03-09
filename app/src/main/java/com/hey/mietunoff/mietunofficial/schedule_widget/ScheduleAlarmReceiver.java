package com.hey.mietunoff.mietunofficial.schedule_widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import timber.log.Timber;

/**
 * Schedule alarm receiver
 * Fires in the start of a day
 */
public class ScheduleAlarmReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.i("start service");
        int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);
        String group = intent.getStringExtra("group");
        startWakefulService(context, ScheduleUpdateService.getScheduleIntent(context,
                ScheduleUpdateService.TODAY_ACTION + String.valueOf(widgetId), widgetId, group));

        ScheduleUpdateService.setupAlarm(context, widgetId, group);
    }

}