package heyalex.com.miet_schedule.schedule_item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.LessonModel;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.data.lessons.LessonsRepository;
import heyalex.com.miet_schedule.model.schedule.DayLessonsModel;
import heyalex.com.miet_schedule.schedule.DaggerScheduleComponent;
import heyalex.com.miet_schedule.schedule.ScheduleActivity;
import heyalex.com.miet_schedule.schedule.ScheduleAdapter;
import heyalex.com.miet_schedule.schedule.ScheduleBuilder;
import heyalex.com.miet_schedule.schedule.ScheduleBuilderHelper;
import heyalex.com.miet_schedule.util.MarginItemDecorator;

/**
 * Created by mac on 10.05.17.
 */

public class ScheduleFragment extends Fragment implements ScheduleItemView, ScheduleAdapter.OnLessonClicked{

    private ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this);
    private int position;

    @BindView(R.id.schedule_list_here)
    RecyclerView scheduleRecycleView;

    @BindView(R.id.no_schedule)
    TextView no_schedule;

    @Inject
    ScheduleBuilderHelper scheduleBuilder;

    public static Fragment newInstance(int position) {
        ScheduleFragment f = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments() != null ? getArguments().getInt("position") : 1;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.schedule_fragment, container, false);
        ButterKnife.bind(this, root);
        if(scheduleBuilder == null){
            ScheduleApp.get(getContext())
                    .getScheduleComponent()
                    .inject(this);
        }
        scheduleRecycleView.setAdapter(scheduleAdapter);
        int horizontal = (int)getResources().getDimension(R.dimen.activity_horizontal_margin);
        int vertical = (int)getResources().getDimension(R.dimen.activity_vertical_margin);
        scheduleRecycleView.addItemDecoration(new MarginItemDecorator(horizontal, vertical));
        scheduleRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(scheduleBuilder != null){
            if(scheduleBuilder.getLessonsForCurrentFragment(position) != null){
                scheduleAdapter.setItems(scheduleBuilder.getLessonsForCurrentFragment(position));
            }
        }

        return root;
    }


    @Override
    public void onLessonClickedListener(LessonModel lesson) {

    }
}
