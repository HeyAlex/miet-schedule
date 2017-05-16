package heyalex.com.miet_schedule.addnewgroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.ScheduleModel;
import heyalex.com.miet_schedule.groups.GroupsAdapter;
import heyalex.com.miet_schedule.navdrawer.DaggerNavDrawerComponent;
import heyalex.com.miet_schedule.navdrawer.NavDrawerPresenter;
import heyalex.com.miet_schedule.util.MarginItemDecorator;

/**
 * Created by mac on 28.04.17.
 */

public class AddNewGroupActivity extends AppCompatActivity implements AddNewGroupView,
        AddNewGroupAdapter.OnGroupClickedListener {

    private AddNewGroupAdapter groupsAdapter = new AddNewGroupAdapter(this);

    @BindView(R.id.group_list)
    RecyclerView recyclerView;

    @Inject
    AddNewGroupPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewgroup);
        ButterKnife.bind(this);
        initViews();
        if(presenter == null){
            ScheduleApp.get(this)
                    .getAddNewGroupComponent()
                    .inject(this);
        }
        presenter.onViewAttached(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showAvailibleGroups(Set<String> groups) {
        groupsAdapter.setItems(groups);
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void onGroupClickedListener(String groupName) {
        presenter.addNewGroup(groupName);
    }

    private void initViews() {
        int margin = (int)getResources().getDimension(R.dimen.dimen_16dp) / 2;
        recyclerView.addItemDecoration(new MarginItemDecorator(margin, margin));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(groupsAdapter);
        //recyclerView.setItemAnimator(new OvershootInLeftAnimator());
    }

}
