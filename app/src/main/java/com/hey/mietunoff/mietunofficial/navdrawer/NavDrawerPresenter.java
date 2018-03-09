package com.hey.mietunoff.mietunofficial.navdrawer;

import com.hey.mietunoff.mietunofficial.util.BasePresenter;

public interface NavDrawerPresenter extends BasePresenter<NavDrawerView> {

    void onNavigationItemClicked(int position);
}
