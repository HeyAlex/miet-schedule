package heyalex.com.miet_schedule.schedule_widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.RemoteViews;

import org.joda.time.DateTime;

import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.util.DateMietHelper;
import heyalex.com.miet_schedule.util.NavigationUtil;
import heyalex.com.miet_schedule.util.VectorUtil;

import static heyalex.com.miet_schedule.schedule_widget.ScheduleUpdateService.TODAY_ACTION;
import static heyalex.com.miet_schedule.schedule_widget.ScheduleUpdateService.TOMORROW_ACTION;

/**
 * Schedule remote view builder that used in {@link ScheduleUpdateService} for building a
 * remote view for widget
 */
/*package*/ class ScheduleRemoteViewBuilder {

    private RemoteViews remoteViews;

    private ScheduleRemoteViewBuilder(Context context) {
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.schedule_app_widget);

    }

    /*package*/
    static Builder newBuilder(Context context, String group, int widgetId,
                              LessonsRepository lessonsRepository) {
        return new ScheduleRemoteViewBuilder(context).new Builder(context, group, widgetId,
                lessonsRepository);
    }

    public class Builder {

        private LessonsRepository lessonsRepository;
        private Context context;
        private String group;
        private int widgetId;
        private int week;
        private int day;

        private Builder(Context context, String group, int widgetId,
                        LessonsRepository lessonsRepository) {
            this.lessonsRepository = lessonsRepository;
            this.context = context;
            this.group = group;
            this.widgetId = widgetId;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                remoteViews.setImageViewResource(R.id.settings_icon, R.drawable.settings);
            } else {
                remoteViews.setImageViewBitmap(R.id.settings_icon, VectorUtil
                        .vectorToBitmap(context, R.drawable.settings));
            }
            remoteViews.setOnClickPendingIntent(R.id.settings_icon, ScheduleUpdateService
                    .getScheduleConfigurationPendingIntent(context, widgetId));
        }

        /*package*/ Builder setTomorrowHeader(boolean shouldBuildTommorow) {
            if (shouldBuildTommorow) {
                this.week = DateMietHelper.getWeek(DateTime.now().plusDays(1));
                this.day = DateMietHelper.getDayInWeek(DateTime.now().plusDays(1));
                remoteViews.setTextViewText(R.id.header, group);
                remoteViews.setOnClickPendingIntent(R.id.header, ScheduleUpdateService
                        .getSchedulePendingIntent(context, group));
                remoteViews.setOnClickPendingIntent(R.id.widget_control, ScheduleUpdateService
                        .getScheduleUpdateServicePendingIntent(context, TODAY_ACTION +
                                String.valueOf(widgetId), widgetId, group));
                remoteViews.setTextViewText(R.id.day, context.getString(R.string.tomorrow_widget_header,
                        NavigationUtil.weekListLong[DateMietHelper.getWeek(DateTime.now()
                                .plusDays(1)) + 2]));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    remoteViews.setImageViewResource(R.id.widget_control, R.drawable.chevron_left);
                } else {
                    remoteViews.setImageViewBitmap(R.id.widget_control, VectorUtil
                            .vectorToBitmap(context, R.drawable.chevron_left));
                }
            }
            return this;
        }

        /*package*/ Builder setTodayHeader(boolean shouldBuildToday) {
            if (shouldBuildToday) {
                this.week = DateMietHelper.getWeek(DateTime.now());
                this.day = DateMietHelper.getDayInWeek(DateTime.now());
                remoteViews.setTextViewText(R.id.header, group);
                remoteViews.setOnClickPendingIntent(R.id.header, ScheduleUpdateService
                        .getSchedulePendingIntent(context, group));
                remoteViews.setOnClickPendingIntent(R.id.widget_control, ScheduleUpdateService
                        .getScheduleUpdateServicePendingIntent(context, TOMORROW_ACTION +
                                String.valueOf(widgetId), widgetId, group));
                remoteViews.setTextViewText(R.id.day, context.getString(R.string.today_widget_header,
                        NavigationUtil.weekListLong[DateMietHelper.getWeek(new DateTime()) + 2]));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    remoteViews.setImageViewResource(R.id.widget_control, R.drawable.chevron_right);
                } else {
                    remoteViews.setImageViewBitmap(R.id.widget_control, VectorUtil
                            .vectorToBitmap(context, R.drawable.chevron_right));
                }
            }
            return this;
        }


        /*package*/ Builder setAdapterForLessons() {
            if (lessonsRepository != null) {
                if (lessonsRepository.getLessonsByWeekAndDay(group, week, day).isEmpty()) {
                    remoteViews.setViewVisibility(R.id.no_schedule_view, View.VISIBLE);
                    remoteViews.setViewVisibility(R.id.lessons, View.INVISIBLE);
                } else {
                    remoteViews.setViewVisibility(R.id.no_schedule_view, View.INVISIBLE);
                    remoteViews.setViewVisibility(R.id.lessons, View.VISIBLE);
                    Intent adapter = new Intent(context, LessonRemoteService.class);
                    adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                    adapter.putExtra("group", group);
                    adapter.putExtra("week", week);
                    adapter.putExtra("day", day);
                    Uri data = Uri.parse(adapter.toUri(Intent.URI_INTENT_SCHEME));
                    adapter.setData(data);
                    remoteViews.setRemoteAdapter(R.id.lessons, adapter);
                }
            }
            return this;
        }

        public RemoteViews build() {
            return remoteViews;
        }

    }

}
