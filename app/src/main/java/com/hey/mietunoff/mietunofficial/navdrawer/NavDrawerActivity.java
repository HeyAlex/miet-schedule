package com.hey.mietunoff.mietunofficial.navdrawer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hey.mietunoff.mietunofficial.news.NewsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.hey.mietunoff.mietunofficial.R;
import com.hey.mietunoff.mietunofficial.ScheduleApp;
import com.hey.mietunoff.mietunofficial.groups.GroupsFragment;


import com.hey.mietunoff.mietunofficial.ui.BaseWebFragement;
import com.hey.mietunoff.mietunofficial.util.NavigationUtil;

public class NavDrawerActivity extends AppCompatActivity implements NavDrawerView {

    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @Nullable
    @BindView(R.id.bottom_navigation)
    public BottomNavigationView bottomNavbar;

    private static final String FRAGMENT_TAG_GROUPS = "FRAGMENT_TAG_GROUPS";
    private static final String FRAGMENT_TAG_ORIOKS = "FRAGMENT_TAG_ORIOKS";
    private static final String FRAGMENT_TAG_NEWS = "FRAGMENT_TAG_NEWS";
    private static final String ORIOKS_URL = "https://orioks.miet.ru/student/student";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    navigateToScheduleGroups();
                    return true;
                case R.id.navigation_dashboard:
                    navigateToNews();
                    return true;
            }
            return false;
        }
    };

    @Inject
    NavDrawerPresenter navDrawerPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        DaggerNavDrawerComponent.builder()
                .applicationComponent(ScheduleApp.get(this).getApplicationComponent())
                .build()
                .inject(this);
        ButterKnife.bind(this);
        bottomNavbar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navDrawerPresenter.onViewAttached(this);
    }

    @Override
    public void navigateToNews() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_NEWS);
        if (fragment == null) {
            fragment = new NewsFragment();
            fragment.setRetainInstance(true);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, fragment, FRAGMENT_TAG_NEWS)
                .commitNow();
    }

    @Override
    public void navigateToScheduleGroups() {
        toolbar.setTitle(R.string.schedule);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_GROUPS);
        if (fragment == null) {
            fragment = new GroupsFragment();
            fragment.setRetainInstance(true);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, fragment, FRAGMENT_TAG_GROUPS)
                .commitNow();

    }

    @Override
    public void navigateToOrioks() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_ORIOKS);
        if (fragment == null) {
            fragment = BaseWebFragement.newInstance(ORIOKS_URL);
            fragment.setRetainInstance(true);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, fragment, FRAGMENT_TAG_ORIOKS)
                .commitNow();

    }

    @Override
    public void showCurrentPosition(int position) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(NavigationUtil.drawerList[position]);
        }
    }

    @Override
    protected void onDestroy() {
        if (navDrawerPresenter != null) {
            navDrawerPresenter.onViewDetached();
        }
        super.onDestroy();
    }
}
