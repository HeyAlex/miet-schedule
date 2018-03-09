package com.hey.mietunoff.mietunofficial.schedule_widget;

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
import com.hey.mietunoff.mietunofficial.R;
import com.hey.mietunoff.mietunofficial.ScheduleApp;
import com.hey.mietunoff.mietunofficial.ScheduleModel;
import com.hey.mietunoff.mietunofficial.addnewgroup.AddNewGroupActivity;
import com.hey.mietunoff.mietunofficial.groups.GroupsAdapter;
import com.hey.mietunoff.mietunofficial.groups.GroupsPresenter;
import com.hey.mietunoff.mietunofficial.groups.GroupsView;
import com.hey.mietunoff.mietunofficial.util.PrefUtils;
import timber.log.Timber;

import static com.hey.mietunoff.mietunofficial.schedule_widget.ScheduleUpdateService.GROUPNAME_KEY;

/**
 * The configuration screen for the {@link ScheduleAppWidget} AppWidget.
 */
public class ScheduleAppWidgetConfigureActivity extends AppCompatActivity implements GroupsView,
        GroupsAdapter.OnGroupClickedListener {

    @BindView(R.id.group_list)
    RecyclerView recyclerView;
    @Inject
    GroupsPresenter presenter;
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private GroupsAdapter groupsAdapter = new GroupsAdapter(this);

    private void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(groupsAdapter);
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

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
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            if (extras.containsKey(GROUPNAME_KEY)) {
                pinWindget(extras.getString(GROUPNAME_KEY));

            }
        }

        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }
    }

    @Override
    public void showHint() {
        //NOPE
    }

    @Override
    public void showGroups(List<ScheduleModel> groups) {
        groupsAdapter.setItems(groups);
    }

    @Override
    public void onGroupClickedListener(ScheduleModel scheduleModel) {
        pinWindget(scheduleModel.getGroup());
    }

    private void pinWindget(String groupName) {
        Intent resultValue = new Intent(this, ScheduleUpdateService.class);
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        resultValue.setAction(ScheduleUpdateService.TODAY_ACTION + String.valueOf(mAppWidgetId));
        resultValue.putExtra("group", groupName);
        this.startService(resultValue);
        Timber.i(String.valueOf(mAppWidgetId));
        PrefUtils.saveToPrefs(this, String.valueOf(mAppWidgetId), groupName);
        ScheduleUpdateService.setupAlarm(this, mAppWidgetId, groupName);
        Intent intentParent = getIntent();
        setResult(RESULT_OK, intentParent);
        finish();
    }

    @Override
    public void onAddNewStaticIcon(String group) {
        presenter.addNewStaticShortcut(group);
    }

    @Override
    public void onRequestWidgetConfigure(String group) {
        //NOPE
    }

    @Override
    public void onDeleteGroup(String group) {
        presenter.deleteGroup(group);
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

