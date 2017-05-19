package heyalex.com.miet_schedule.schedule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import heyalex.com.miet_schedule.model.schedule.DayLessonsModel;
import heyalex.com.miet_schedule.schedule_builder.ScheduleBuilderHelper;
import heyalex.com.miet_schedule.util.MarginItemDecorator;
import heyalex.com.miet_schedule.util.NavigationUtil;

/**
 * Created by mac on 10.05.17.
 */

public class ScheduleFragment extends Fragment implements ScheduleAdapter.OnLessonClicked {

    private ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this);
    private static final String POSITION = "position";
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
        args.putInt(POSITION, position);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments() != null ? getArguments().getInt(POSITION) : 0;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.schedule_fragment, container, false);
        ButterKnife.bind(this, root);
        if (scheduleBuilder == null) {
            ScheduleApp.get(getContext())
                    .getScheduleComponent()
                    .inject(this);
        }
        scheduleRecycleView.setAdapter(scheduleAdapter);
        int horizontal = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
        int vertical = (int) getResources().getDimension(R.dimen.activity_vertical_margin);
        scheduleRecycleView.addItemDecoration(new MarginItemDecorator(horizontal, vertical));
        scheduleRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (scheduleBuilder != null) {
            if (scheduleBuilder.getLessonsForCurrentFragment(position) != null) {
                final List<DayLessonsModel> dayLesson = scheduleBuilder
                        .getLessonsForCurrentFragment(position);

                if (dayLesson.size() != 0) {
                    if(dayLesson.get(0).getLessons().size() > 0){
                        scheduleAdapter.setItems(scheduleBuilder
                                .getLessonsForCurrentFragment(position));
                        no_schedule.setVisibility(View.INVISIBLE);
                        scheduleRecycleView.setVisibility(View.VISIBLE);
                    } else {
                        no_schedule.setVisibility(View.VISIBLE);
                        scheduleRecycleView.setVisibility(View.INVISIBLE);
                        no_schedule.setText(getString(R.string.no_schedule_for_current_day,
                                NavigationUtil.weekListLong[position]));
                    }
                } else {
                    no_schedule.setVisibility(View.VISIBLE);
                    scheduleRecycleView.setVisibility(View.INVISIBLE);
                    no_schedule.setText(getString(R.string.no_schedule_for_current_day,
                            NavigationUtil.weekListLong[position]));
                }
            }
        }
        return root;
    }


    @Override
    public void onLessonClickedListener(LessonModel lesson) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.dialog_lesson_item, null);
        TextView name = (TextView) customView.findViewById(R.id.name);
        TextView teacher = (TextView) customView.findViewById(R.id.tutor);
        TextView room = (TextView) customView.findViewById(R.id.room);
        TextView time = (TextView) customView.findViewById(R.id.time);

        name.setText(lesson.getDisciplineName());
        room.setText(lesson.getRoom());
        time.setText(lesson.getTimeFull());
        teacher.setText(lesson.getTeacherFull());

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getContext());
        builder.setView(customView);
        builder.setTitle(lesson.getDisciplineType());
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
