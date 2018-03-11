package com.alex.miet.mobile.groups;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.alex.miet.mobile.R;
import com.alex.miet.mobile.ScheduleApp;
import com.alex.miet.mobile.ScheduleModel;
import com.alex.miet.mobile.addnewgroup.AddNewGroupActivity;
import com.alex.miet.mobile.schedule.ScheduleActivity;

public class GroupsFragment extends Fragment implements GroupsView, GroupsAdapter.OnGroupClickedListener {

    @BindView(R.id.group_list)
    RecyclerView recyclerView;
    @Inject
    GroupsPresenter presenter;
    private GroupsAdapter groupsAdapter = new GroupsAdapter(this);

    private void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(groupsAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.groups_fragement, container, false);
        ButterKnife.bind(this, root);
        initViews();
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter == null) {
            ScheduleApp.get(getContext())
                    .getGroupsComponent()
                    .inject(this);
        }
        presenter.onViewAttached(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.onViewDetached();
        }
    }

    @Override
    public void showHint() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.showGroups();
    }

    @Override
    public void showGroups(List<ScheduleModel> groups) {
        groupsAdapter.setItems(groups);
    }

    @Override
    public void onGroupClickedListener(ScheduleModel scheduleModel) {
        Intent intent = new Intent(getActivity(), ScheduleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("group", scheduleModel.getGroup());
        startActivity(intent);
    }

    @Override
    public void onAddNewStaticIcon(String group) {
        presenter.addNewStaticShortcut(group);
    }

    @Override
    public void onRequestWidgetConfigure(String groupName) {
        presenter.requestWidget(groupName);
    }

    @Override
    public void onDeleteGroup(String group) {
        presenter.deleteGroup(group);
    }


    @OnClick(R.id.add_group)
    public void AddNewGroup() {
        Intent intent = new Intent(getActivity(), AddNewGroupActivity.class);
        startActivity(intent);
    }

}
