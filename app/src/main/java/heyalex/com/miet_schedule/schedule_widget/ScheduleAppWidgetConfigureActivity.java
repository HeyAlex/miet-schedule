package heyalex.com.miet_schedule.schedule_widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import heyalex.com.miet_schedule.util.PrefUtils;
import timber.log.Timber;

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
        resultValue.setAction(ScheduleUpdateService.TODAY_ACTION +String.valueOf(mAppWidgetId));
        resultValue.putExtra("group", newsModel.getGroup());
        this.startService(resultValue);
        Timber.i(String.valueOf(mAppWidgetId));
        PrefUtils.saveToPrefs(this, String.valueOf(mAppWidgetId),newsModel.getGroup());
        ScheduleUpdateService.setupAlarm(this, mAppWidgetId, newsModel.getGroup());
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
}

