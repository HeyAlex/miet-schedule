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

import javax.inject.Inject;

import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.model.schedule.Time;
import heyalex.com.miet_schedule.schedule.ScheduleActivity;
import heyalex.com.miet_schedule.util.DateMietHelper;
import heyalex.com.miet_schedule.util.NavigationUtil;
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
        if(intent != null){
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);
            String group = intent.getStringExtra("group");
            Timber.i("ScheduleUpdateService onHandleIntent");
            if (intent.getAction() != null){
                if(intent.getAction().startsWith(TOMORROW_ACTION)){
                    RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.schedule_app_widget);
                    remoteViews.setInt(R.id.widget_control, "setBackgroundResource", R.drawable.chevron_left);
                    remoteViews.setTextViewText(R.id.header,group);
                    remoteViews.setOnClickPendingIntent(R.id.header,getSchedulePendingIntent(this, group));
                    remoteViews.setOnClickPendingIntent(R.id.widget_control, getPingPendingIntent(this, TODAY_ACTION + String.valueOf(widgetId),widgetId,group));
                    remoteViews.setTextViewText(R.id.day,"ЗАВТРА" + " " +
                            NavigationUtil.weekListLong[DateMietHelper.getWeek(DateTime.now().plusDays(1)) + 2]);

                    if(lessonsRepository.getLessonsByWeekAndDay( group,
                            DateMietHelper.getWeek(DateTime.now().plusDays(1)),
                            DateMietHelper.getDayInWeek(DateTime.now().plusDays(1))).isEmpty()){
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
                        adapter.putExtra("week", DateMietHelper.getWeek(DateTime.now().plusDays(1)));
                        adapter.putExtra("day", DateMietHelper.getDayInWeek(DateTime.now().plusDays(1)));
                        Uri data = Uri.parse(adapter.toUri(Intent.URI_INTENT_SCHEME));
                        adapter.setData(data);
                        remoteViews.setRemoteAdapter(R.id.lessons, adapter);
                        appWidgetManager.updateAppWidget(widgetId, remoteViews);
                        appWidgetManager.notifyAppWidgetViewDataChanged(widgetId,
                                R.id.lessons);
                    }

                } else if(intent.getAction().startsWith(TODAY_ACTION)){
                    Timber.i("ScheduleUpdateService TODAY_ACTION");
                    RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.schedule_app_widget);
                    remoteViews.setTextViewText(R.id.day,"СЕГОДНЯ" + " " +
                            NavigationUtil.weekListLong[DateMietHelper.getWeek(DateTime.now()) + 2]);
                    remoteViews.setInt(R.id.widget_control, "setBackgroundResource", R.drawable.chevron_right);
                    remoteViews.setTextViewText(R.id.header, group);
                    remoteViews.setOnClickPendingIntent(R.id.header,getSchedulePendingIntent(this, group));
                    remoteViews.setOnClickPendingIntent(R.id.widget_control, getPingPendingIntent(this, TOMORROW_ACTION + String.valueOf(widgetId),widgetId,group));

                    if(lessonsRepository.getLessonsByWeekAndDay( group,
                            DateMietHelper.getWeek(DateTime.now()),
                            DateMietHelper.getDayInWeek(DateTime.now())).isEmpty()){
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
                        adapter.putExtra("week", DateMietHelper.getWeek(DateTime.now()));
                        adapter.putExtra("day", DateMietHelper.getDayInWeek(DateTime.now()));
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

    public static PendingIntent getSchedulePendingIntent(Context context, String group) {
        Intent resultValue = new Intent(context, ScheduleActivity.class);
        resultValue.putExtra("group", group);
        return PendingIntent.getActivity(context, 0, resultValue, 0);
    }

    public static Intent getScheduleIntent(Context context, String action, int widgetId, String group) {
        Intent resultValue = new Intent(context, ScheduleUpdateService.class);
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        resultValue.putExtra("group", group);
        resultValue.setAction(action);
        return resultValue;
    }

    public static void setupAlarm(Context context,int widgetId, String group) {
        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = getPingPendingIntent(context, TODAY_ACTION + String.valueOf(widgetId),widgetId,group);
        alarmManager.cancel(pendingIntent);

        Timber.i("Time for a next trigger of schedule widget update is %s", DateTime.now().withTimeAtStartOfDay().toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, DateTime.now().withTimeAtStartOfDay().getMillis(), pendingIntent);
        } else
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, DateTime.now().withTimeAtStartOfDay().getMillis(), 0, pendingIntent);
    }

    public static void stopAlarm(Context context,int widgetId, String group) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(getPingPendingIntent(context, TODAY_ACTION + String.valueOf(widgetId),widgetId,group));
    }
}
