package heyalex.com.miet_schedule.schedule_widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;

import heyalex.com.miet_schedule.util.PrefUtils;
import timber.log.Timber;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link ScheduleAppWidgetConfigureActivity}
 */
public class ScheduleAppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Timber.i("ScheduleAppWidget onUpdate");
        for (int widget_id : appWidgetIds) {
            Timber.i("Update widget with id %s", String.valueOf(widget_id));
            String groupName = PrefUtils.getFromPrefs(context, String.valueOf(widget_id), "");
            if(groupName != null){
                context.startService(ScheduleUpdateService.getScheduleIntent(context,
                        ScheduleUpdateService.TODAY_ACTION + String.valueOf(widget_id), widget_id,
                        groupName));
            } else {
                context.startService(ScheduleUpdateService.getScheduleIntent(context,
                        ScheduleUpdateService.TODAY_ACTION + String.valueOf(widget_id), widget_id,
                        "Нет группы"));
            }
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        for (int widgetId : appWidgetIds) {
            String groupName = PrefUtils.getFromPrefs(context, String.valueOf(widgetId), "");
            Timber.i("Deleting a widget with id- %s and group name- %s", widgetId, groupName);
            PrefUtils.removeFromPrefs(context, String.valueOf(widgetId));
            ScheduleUpdateService.stopAlarm(context, widgetId, groupName);
        }
    }


    @Override
    public void onEnabled(Context context) {
        Timber.i("ScheduleAppWidget onEnabled");
        // Enter relevant functionality for when the first widget is created
        final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int widgets_IDs[] = appWidgetManager.getAppWidgetIds(new ComponentName(context,
                ScheduleAppWidget.class));
        for (int widget_id : widgets_IDs) {
            Timber.i("Update widget with id %s", String.valueOf(widget_id));
            String groupName = PrefUtils.getFromPrefs(context, String.valueOf(widget_id), "");
            context.startService(ScheduleUpdateService.getScheduleIntent(context,
                    ScheduleUpdateService.TODAY_ACTION + String.valueOf(widget_id), widget_id,
                    groupName));
            ScheduleUpdateService.setupAlarm(context, widget_id, groupName);
        }
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

