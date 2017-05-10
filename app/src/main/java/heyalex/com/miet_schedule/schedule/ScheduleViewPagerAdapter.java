package heyalex.com.miet_schedule.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import heyalex.com.miet_schedule.schedule_item.ScheduleFragment;

/**
 * Created by mac on 10.05.17.
 */

public class ScheduleViewPagerAdapter extends FragmentStatePagerAdapter {

    static final int NUM_ITEMS = 6;

    public ScheduleViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ScheduleFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
