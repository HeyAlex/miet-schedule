package com.alex.miet.mobile.addnewgroup;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.alex.miet.mobile.R;
import com.alex.miet.mobile.ScheduleApp;

public class AddNewGroupActivity extends AppCompatActivity implements AddNewGroupView,
        AddNewGroupAdapter.OnGroupClickedListener {

    @BindView(R.id.addnewgroup_root)
    View root;
    @BindView(R.id.retry_download_groups)
    TextView retryDownloadGroups;
    @BindView(R.id.group_list)
    RecyclerView recyclerView;
    @BindView(R.id.search_edittext)
    EditText search_edittext;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    AddNewGroupPresenter presenter;
    private AddNewGroupAdapter groupsAdapter = new AddNewGroupAdapter(this);
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewgroup);
        ButterKnife.bind(this);
        setupToolbar();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initViews();


        if (presenter == null) {
            ScheduleApp.get(this)
                    .getAddNewGroupComponent()
                    .inject(this);
        }
        presenter.onViewAttached(this);

        search_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //NOP
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!search_edittext.getText().toString().isEmpty()) {
                    presenter.onSearch(search_edittext.getText().toString());
                } else {
                    presenter.onSearchCanceled();
                }
                recyclerView.scrollToPosition(0);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //NOP
            }
        });

    }


    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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
    public void showAvailibleGroups(List<String> groups) {
        retryDownloadGroups.setVisibility(View.INVISIBLE);
        groupsAdapter.setItems(groups);
    }

    @Override
    public void showErrorView(String errorName) {
        Snackbar.make(root, getString(R.string.error_add_new_group, errorName)
                , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showDownloadingAvailibleGroups() {
        progressDialog.setMessage(getString(R.string.addnewgroup_downloading_availible_groups));
        progressDialog.show();
    }

    @Override
    public void showDownloadingGroup(String group) {
        progressDialog.setMessage(getString(R.string.Downloading_add_new_group, group));
        progressDialog.show();
    }

    @Override
    public void hideDownloading() {
        progressDialog.dismiss();
    }

    @Override
    public void finishPickingGroups() {
        progressDialog.dismiss();
        finish();
    }

    @Override
    public void showRetryButton() {
        retryDownloadGroups.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGroupClickedListener(String groupName) {
        presenter.addNewGroup(groupName);
    }

    private void initViews() {
        progressDialog = new ProgressDialog(AddNewGroupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.addnewgroup_downloading));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                presenter.onCancelDownloadingGroup();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(groupsAdapter);
        retryDownloadGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showGroups();
            }
        });
    }
}
