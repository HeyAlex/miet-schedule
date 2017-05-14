package heyalex.com.miet_schedule.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import javax.inject.Inject;

import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.model.schedule.CycleWeeksLessonModel;
import heyalex.com.miet_schedule.schedule_builder.ScheduleBuilderHelper;

/**
 * Created by alexf on 07.04.2017.
 */

public class ScheduleActivity extends AppCompatActivity implements ScheduleView{

    private ScheduleViewPagerAdapter pagerAdapter;

    private ViewPager pager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Inject
    SchedulePresenter presenter;

    @Inject
    ScheduleBuilderHelper scheduleBuilder;

    private String groupName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        pager = (ViewPager) findViewById(R.id.schedule_viewpager);
        groupName = getIntent().getStringExtra("group");
        toolbar.setTitle(groupName);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (presenter == null) {
            ScheduleApp.get(this)
                    .getScheduleComponent()
                    .inject(this);
            presenter.getCachedScheduleForGroup(groupName);
        }
        presenter.onViewAttached(this);
        pagerAdapter = new ScheduleViewPagerAdapter(getSupportFragmentManager());
        tabLayout.addTab(tabLayout.newTab().setText("Сегодня"));
        tabLayout.addTab(tabLayout.newTab().setText("Завтра"));
        tabLayout.addTab(tabLayout.newTab().setText("ЧИСЛ 1"));
        tabLayout.addTab(tabLayout.newTab().setText("ЗНАМ 1"));
        tabLayout.addTab(tabLayout.newTab().setText("ЧИСЛ 2"));
        tabLayout.addTab(tabLayout.newTab().setText("ЗНАМ 2"));
        allotEachTabWithEqualWidth();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(pager != null){
            pager.clearOnPageChangeListeners();
            tabLayout.clearOnTabSelectedListeners();
        }
        presenter.onViewDetached();
    }

    @Override
    public void showSchedule(CycleWeeksLessonModel schedule) {
        scheduleBuilder.setBuildedLessonSchedule(schedule);
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener (new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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

