package com.hey.mietunoff.mietunofficial.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hey.mietunoff.mietunofficial.model.schedule.CycleWeeksLessonModel;
import com.hey.mietunoff.mietunofficial.schedule_builder.ScheduleBuilderHelper;
import com.hey.mietunoff.mietunofficial.util.NavigationUtil;

import org.joda.time.DateTime;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.hey.mietunoff.mietunofficial.R;
import com.hey.mietunoff.mietunofficial.ScheduleApp;

import com.hey.mietunoff.mietunofficial.util.DateMietHelper;

import timber.log.Timber;

public class ScheduleActivity extends AppCompatActivity implements ScheduleView {

    private static final String GROUP = "group";
    @BindView(R.id.schedule_activity_root)
    View schedule_root;
    @BindView(R.id.schedule_viewpager)
    ViewPager pager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    SchedulePresenter presenter;
    @Inject
    ScheduleBuilderHelper scheduleBuilder;
    private int currentPosition = 0;
    private ScheduleViewPagerAdapter pagerAdapter;
    private String groupName;
    private Snackbar updatingSnackbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);
        ButterKnife.bind(this);
        groupName = getIntent().getStringExtra(GROUP);
        toolbar.setTitle(groupName);
        this.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        updatingSnackbar = initSnackBar();
        pagerAdapter = new ScheduleViewPagerAdapter(getSupportFragmentManager());
        if (presenter == null) {
            ScheduleApp.get(this)
                    .getScheduleComponent()
                    .inject(this);

            presenter.onViewAttached(this);
            presenter.getCachedScheduleForGroup(groupName);
        }

        for (String tabHeader : NavigationUtil.weekList) {
            tabLayout.addTab(tabLayout.newTab().setText(tabHeader));
        }
        allotEachTabWithEqualWidth();
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                currentPosition = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Timber.d("onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.schedule_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.schedule_go_to_current_week);
        if (!menuItem.isEnabled()) {
            menuItem.setEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Timber.d("onOptionsItemSelected");
        int id = item.getItemId();
        if (id == R.id.schedule_action_update) {
            item.setEnabled(false);
            presenter.updateScheduleForGroup(groupName);
            return true;
        }
        if (id == R.id.schedule_go_to_current_week) {
            if (tabLayout.getSelectedTabPosition() != DateMietHelper.getWeek(DateTime.now()) + 2) {
                pager.setCurrentItem(DateMietHelper.getWeek(DateTime.now()) + 2, true);
            } else {
                Snackbar.make(schedule_root, R.string.schedule_error_this_week,
                        Snackbar.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Snackbar initSnackBar() {
        return Snackbar.make(schedule_root, getString(R.string.schedule_updating_group, groupName),
                Snackbar.LENGTH_INDEFINITE);
    }

    @Override
    protected void onDestroy() {
        Timber.d("onDestroy");
        super.onDestroy();
        pager.clearOnPageChangeListeners();
        tabLayout.clearOnTabSelectedListeners();
        if (presenter != null) {
            presenter.onViewDetached();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GROUP, groupName);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        groupName = savedInstanceState.getString(GROUP);
    }

    @Override
    public void showStatus(boolean state) {
        if (state) {
            updatingSnackbar.show();
        } else {
            updatingSnackbar.dismiss();
        }
    }

    @Override
    public void showSchedule(CycleWeeksLessonModel schedule) {
        scheduleBuilder.setBuildedLessonSchedule(schedule);
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(currentPosition);
        pagerAdapter.notifyDataSetChanged();
        invalidateOptionsMenu();
    }

    @Override
    public void showErrorView() {
        Snackbar.make(schedule_root, getString(R.string.schedule_error_while_updating, groupName),
                Snackbar.LENGTH_SHORT).show();
    }

    private void allotEachTabWithEqualWidth() {
        ViewGroup slidingTabStrip = (ViewGroup) tabLayout.getChildAt(0);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View tab = slidingTabStrip.getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
                    tab.getLayoutParams();
            layoutParams.weight = 1;
            tab.setLayoutParams(layoutParams);
        }
    }
}

