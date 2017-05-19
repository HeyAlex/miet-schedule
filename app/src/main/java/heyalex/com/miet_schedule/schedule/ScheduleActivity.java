package heyalex.com.miet_schedule.schedule;

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

import org.joda.time.DateTime;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;
import heyalex.com.miet_schedule.schedule_builder.ScheduleBuilderHelper;
import heyalex.com.miet_schedule.util.DateMietHelper;
import heyalex.com.miet_schedule.util.NavigationUtil;

/**
 * Created by alexf on 07.04.2017.
 */

public class ScheduleActivity extends AppCompatActivity implements ScheduleView {

    private ScheduleViewPagerAdapter pagerAdapter;
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

    private String groupName;
    private Snackbar updatingSnacbar;

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
        updatingSnacbar = initSnackBar();
        if (presenter == null) {
            ScheduleApp.get(this)
                    .getScheduleComponent()
                    .inject(this);
            presenter.getCachedScheduleForGroup(groupName);
        }
        presenter.onViewAttached(this);
        pagerAdapter = new ScheduleViewPagerAdapter(getSupportFragmentManager());
        for (String tabHeader : NavigationUtil.weekList) {
            tabLayout.addTab(tabLayout.newTab().setText(tabHeader));
        }
        allotEachTabWithEqualWidth();
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
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
        getMenuInflater().inflate(R.menu.schedule_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.schedule_action_update) {
            presenter.updateScheduleForGroup(groupName);
            return true;
        }
        if (id == R.id.schedule_go_to_current_week) {
            if (tabLayout.getSelectedTabPosition() != DateMietHelper.getWeekByDay(DateTime.now()) % 4) {
                pager.setCurrentItem(DateMietHelper.getWeekByDay(DateTime.now()) % 4);
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
        super.onDestroy();
        if (pager != null) {
            pager.clearOnPageChangeListeners();
            tabLayout.clearOnTabSelectedListeners();
        }
        presenter.onViewDetached();
    }

    @Override
    public void showSchedule(CycleWeeksLessonModel schedule) {
        scheduleBuilder.setBuildedLessonSchedule(schedule);
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public void showStatus(boolean state) {
        if (state) {
            updatingSnacbar.show();
        } else {
            updatingSnacbar.dismiss();
        }
    }

    @Override
    public void showReloadedSchedule(CycleWeeksLessonModel schedule) {
        scheduleBuilder.setBuildedLessonSchedule(schedule);
        pagerAdapter.notifyDataSetChanged();
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

