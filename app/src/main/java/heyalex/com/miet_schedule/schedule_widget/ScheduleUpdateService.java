package heyalex.com.miet_schedule.schedule_widget;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RemoteViews;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import javax.inject.Inject;

import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.schedule.ScheduleActivity;
import heyalex.com.miet_schedule.util.DateMietHelper;
import heyalex.com.miet_schedule.util.NavigationUtil;
import heyalex.com.miet_schedule.util.VectorUtil;
import timber.log.Timber;

/**
 * Created by alexf on 13.04.2017.
 */

public class ScheduleUpdateService extends IntentService {

    public static final String TOMORROW_ACTION = "TOMORROW_ACTION";
    public static final String TODAY_ACTION = "TODAY_ACTION";

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
        if (intent != null) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);

            Timber.i("ScheduleUpdateService onHandleIntent");
            if (intent.getAction() != null) {
                if (intent.getAction().startsWith(TOMORROW_ACTION)) {
                    String group = intent.getStringExtra("group");
                    int week = DateMietHelper.getWeek(DateTime.now().plusDays(1));
                    int day = DateMietHelper.getDayInWeek(DateTime.now().plusDays(1));
                    boolean isNoLessons = lessonsRepository.getLessonsByWeekAndDay(group, week, day)
                            .isEmpty();

                    RemoteViews views = ScheduleRemoteViewBuilder.newBuilder(this, group, widgetId)
                            .setTomorrowHeader()
                            .setAdapterForLessons(isNoLessons, week, day)
                            .build();
                    appWidgetManager.updateAppWidget(widgetId, views);
                    appWidgetManager.notifyAppWidgetViewDataChanged(widgetId, R.id.lessons);

                } else if (intent.getAction().startsWith(TODAY_ACTION)) {

                    String group = intent.getStringExtra("group");
                    int week = DateMietHelper.getWeek(DateTime.now());
                    int day = DateMietHelper.getDayInWeek(DateTime.now());
                    boolean isNoLessons = lessonsRepository.getLessonsByWeekAndDay(group, week, day)
                            .isEmpty();

                    RemoteViews views = ScheduleRemoteViewBuilder.newBuilder(this, group, widgetId)
                            .setTodayHeader()
                            .setAdapterForLessons(isNoLessons, week, day)
                            .build();
                    appWidgetManager.updateAppWidget(widgetId, views);
                    appWidgetManager.notifyAppWidgetViewDataChanged(widgetId, R.id.lessons);
                }
            }
        }
    }

    public static PendingIntent getScheduleUpdateServicePendingIntent(Context context, String action,
                                                                      int widgetId, String group) {
        Intent resultValue = new Intent(context, ScheduleUpdateService.class);
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        resultValue.putExtra("group", group);
        resultValue.setAction(action);
        return PendingIntent.getService(context, 0, resultValue, 0);
    }

    public static PendingIntent getAlarmIntent(Context context, String action, int widgetId,
                                               String group) {
        Intent resultValue = new Intent(context, ScheduleAlarmReceiver.class);
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        resultValue.putExtra("group", group);
        resultValue.setAction(action);
        return PendingIntent.getBroadcast(context, 0, resultValue, 0);
    }


    public static PendingIntent getSchedulePendingIntent(Context context, String group) {
        Intent resultValue = new Intent(context, ScheduleActivity.class);
        resultValue.putExtra("group", group);
        resultValue.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        Uri data = Uri.parse(resultValue.toUri(Intent.URI_INTENT_SCHEME));
        resultValue.setData(data);
        return PendingIntent.getActivity(context, 0, resultValue, 0);
    }

    public static Intent getScheduleIntent(Context context, String action, int widgetId,
                                           String group) {
        Intent resultValue = new Intent(context, ScheduleUpdateService.class);
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        resultValue.putExtra("group", group);
        resultValue.setAction(action);
        return resultValue;
    }

    public static void setupAlarm(Context context, int widgetId, String group) {
        final AlarmManager alarmManager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = getAlarmIntent(context, ScheduleUpdateService.TODAY_ACTION
                + String.valueOf(widgetId), widgetId, group);
        alarmManager.cancel(pendingIntent);
        DateTime todayStart = new DateTime();
        DateTime tomorrowStart = todayStart.plusDays(1).withTimeAtStartOfDay();
        long ml = new Duration(todayStart, tomorrowStart).getMillis();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Timber.i("Time for a next trigger of schedule widget update is %s", String.valueOf(ml));
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, tomorrowStart.getMillis(),
                    pendingIntent);
        } else {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, tomorrowStart.getMillis(),
                        pendingIntent);
            }else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, tomorrowStart.getMillis(), pendingIntent);
            }

        }
    }

    public static void stopAlarm(Context context, int widgetId, String group) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(getScheduleUpdateServicePendingIntent(context, TODAY_ACTION
                + String.valueOf(widgetId), widgetId, group));
    }
}
