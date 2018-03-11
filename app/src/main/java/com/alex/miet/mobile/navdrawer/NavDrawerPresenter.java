package com.alex.miet.mobile.navdrawer;

import com.alex.miet.mobile.util.BasePresenter;

public interface NavDrawerPresenter extends BasePresenter<NavDrawerView> {

    void onNavigationItemClicked(int position);
}
