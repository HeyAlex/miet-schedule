package heyalex.com.miet_schedule.schedule_widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
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
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

