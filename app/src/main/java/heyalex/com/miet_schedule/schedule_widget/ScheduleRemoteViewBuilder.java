package heyalex.com.miet_schedule.schedule_widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.RemoteViews;

import org.joda.time.DateTime;

import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.util.DateMietHelper;
import heyalex.com.miet_schedule.util.NavigationUtil;
import heyalex.com.miet_schedule.util.VectorUtil;

import static heyalex.com.miet_schedule.schedule_widget.ScheduleUpdateService.TODAY_ACTION;
import static heyalex.com.miet_schedule.schedule_widget.ScheduleUpdateService.TOMORROW_ACTION;

/**
 * Created by alexf on 21.05.2017.
 */

public class ScheduleRemoteViewBuilder {

    private RemoteViews remoteViews;
    private Context context;

    private ScheduleRemoteViewBuilder(Context context){
       remoteViews = new RemoteViews(context.getPackageName(), R.layout.schedule_app_widget);
    }

    public static Builder newBuilder(Context context, String group, int widgetId) {
     return new ScheduleRemoteViewBuilder(context).new Builder(context, group, widgetId);
    }

    public class Builder {

        private Context context;
        private String group;
        private int widgetId;
        private Builder(Context context, String group, int widgetId) {
            this.context = context;
            this.group = group;
            this.widgetId = widgetId;
        }

        public Builder setTomorrowHeader(){
            remoteViews.setTextViewText(R.id.header, group);
            remoteViews.setOnClickPendingIntent(R.id.header, ScheduleUpdateService
                    .getSchedulePendingIntent(context, group));
            remoteViews.setOnClickPendingIntent(R.id.widget_control,ScheduleUpdateService
                    .getScheduleUpdateServicePendingIntent(context, TODAY_ACTION +
                            String.valueOf(widgetId), widgetId, group));
            remoteViews.setTextViewText(R.id.day, context.getString(R.string.tomorrow_widget_header,
                    NavigationUtil.weekListLong[DateMietHelper.getWeek(DateTime.now()
                            .plusDays(1)) + 2]));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                remoteViews.setImageViewResource(R.id.widget_control, R.drawable.chevron_left);
            }else {
                remoteViews.setImageViewBitmap(R.id.widget_control, VectorUtil.vectorToBitmap(context
                        ,R.drawable.chevron_left));
            }
            return this;
        }

        public Builder setTodayHeader(){
            remoteViews.setTextViewText(R.id.header, group);
            remoteViews.setOnClickPendingIntent(R.id.header, ScheduleUpdateService
                    .getSchedulePendingIntent(context, group));
            remoteViews.setOnClickPendingIntent(R.id.widget_control,ScheduleUpdateService
                    .getScheduleUpdateServicePendingIntent(context, TOMORROW_ACTION +
                            String.valueOf(widgetId), widgetId, group));
            remoteViews.setTextViewText(R.id.day, context.getString(R.string.today_widget_header,
                    NavigationUtil.weekListLong[DateMietHelper.getWeek(new DateTime()) + 2]));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                remoteViews.setImageViewResource(R.id.widget_control, R.drawable.chevron_right);
            }else {
                remoteViews.setImageViewBitmap(R.id.widget_control, VectorUtil.vectorToBitmap(context
                        ,R.drawable.chevron_right));
            }
            return this;
        }


        public Builder setAdapterForLessons(boolean isEmpty, int week, int day){
            if(isEmpty){
                remoteViews.setViewVisibility(R.id.no_schedule_view, View.VISIBLE);
                remoteViews.setViewVisibility(R.id.lessons, View.INVISIBLE);
            }else {
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
            return this;
        }

        public RemoteViews build() {
            return remoteViews;
        }

    }

}
