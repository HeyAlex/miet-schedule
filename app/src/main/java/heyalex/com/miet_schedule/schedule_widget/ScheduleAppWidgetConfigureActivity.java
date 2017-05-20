package heyalex.com.miet_schedule.schedule_widget;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.joda.time.DateTime;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.addnewgroup.AddNewGroupActivity;
import heyalex.com.miet_schedule.groups.GroupsAdapter;
import heyalex.com.miet_schedule.groups.GroupsPresenter;
import heyalex.com.miet_schedule.groups.GroupsView;
import heyalex.com.miet_schedule.schedule.ScheduleActivity;

/**
 * The configuration screen for the {@link ScheduleAppWidget ScheduleAppWidget} AppWidget.
 */
public class ScheduleAppWidgetConfigureActivity extends AppCompatActivity implements GroupsView,
        GroupsAdapter.OnGroupClickedListener {

    private GroupsAdapter groupsAdapter = new GroupsAdapter(this);

    @BindView(R.id.group_list)
    RecyclerView recyclerView;

    @Inject
    GroupsPresenter presenter;
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    private void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(groupsAdapter);
    }


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.schedule_app_widget_configure);
        ButterKnife.bind(this);
        initViews();

        if (presenter == null) {
            ScheduleApp.get(this)
                    .getGroupsComponent()
                    .inject(this);
        }
        presenter.onViewAttached(this);
        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

    }

    @Override
    public void showHint() {

    }

    @Override
    public void showGroups(List<ScheduleModel> groups) {
        groupsAdapter.setItems(groups);
    }

    @Override
    public void onGroupClickedListener(ScheduleModel newsModel) {
        Intent resultValue = new Intent(this, ScheduleUpdateService.class);
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        resultValue.putExtra("group", newsModel.getGroup());
        this.startService(resultValue);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.showGroups();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onViewDetached();
        }
    }

    @OnClick(R.id.add_group)
    public void AddNewGroup() {
        Intent intent = new Intent(this, AddNewGroupActivity.class);
        startActivity(intent);
    }
//
//    public static void setupAlarm(Context context) {
//        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        PendingIntent pendingIntent = getPendingIntent(context);
//        alarmManager.cancel(pendingIntent);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, DateTime.now().withTimeAtStartOfDay(), pendingIntent);
//        }
//        else alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60 * 1000, pendingIntent);
//    }
//
//    public static void stopAlarm(Context context) {
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        alarmManager.cancel(getPendingIntent(context));
//    }
//
//    public static PendingIntent getPendingIntent(Context context) {
//        Intent intent = new Intent(context, AlarmReceiver.class);
//        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//    }
}

