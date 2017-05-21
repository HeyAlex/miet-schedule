package heyalex.com.miet_schedule.schedule_widget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import heyalex.com.miet_schedule.util.PrefUtils;
import timber.log.Timber;

/**
 * Created by alexf on 21.05.2017.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.i("BootBroadcastReceiver triggered");
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Timber.i("BOOT_COMPLETED action");
            final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int widgets_IDs[] = appWidgetManager.getAppWidgetIds(new ComponentName(context,
                    ScheduleAppWidget.class));
            for (int widget_id:widgets_IDs) {
                Timber.i("Update widget with id %s", String.valueOf(widget_id));
                context.startService(ScheduleUpdateService.getScheduleIntent(context,
                        ScheduleUpdateService.TODAY_ACTION + String.valueOf(widget_id), widget_id,
                        PrefUtils.getFromPrefs(context,String.valueOf(widget_id),"")));
            }
        }
    }
}
