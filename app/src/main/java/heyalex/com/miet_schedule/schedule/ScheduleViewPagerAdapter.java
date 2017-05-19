package heyalex.com.miet_schedule.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


/**
 * Created by mac on 10.05.17.
 */

/*package*/ class ScheduleViewPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_ITEMS = 6;

    /*package*/ ScheduleViewPagerAdapter(FragmentManager fm) {
        super(fm) ;
    }

    @Override
    public Fragment getItem(int position) {
        return ScheduleFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
