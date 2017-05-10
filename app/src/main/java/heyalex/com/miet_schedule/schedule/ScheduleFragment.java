package heyalex.com.miet_schedule.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleModel;

/**
 * Created by mac on 10.05.17.
 */

public class ScheduleFragment extends Fragment {

    @BindView(R.id.schedule_list)
    RecyclerView mScheduleRecycleView;

    @BindView(R.id.no_schedule)
    TextView no_schedule;

    public static Fragment newInstance(int position) {
        return new ScheduleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.schedule_fragment, container, false);
        ButterKnife.bind(this, root);
        initRecyceView();
        return root;
    }

    private void initRecyceView() {
    }

    public void setSchedule(List<ScheduleModel> model){

    }
}
