package heyalex.com.miet_schedule.addnewgroup;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.R;
import heyalex.com.miet_schedule.ScheduleApp;
import heyalex.com.miet_schedule.schedule.ScheduleActivity;

/**
 * Created by mac on 28.04.17.
 */

public class AddNewGroupActivity extends AppCompatActivity implements AddNewGroupView,
        AddNewGroupAdapter.OnGroupClickedListener {

    private AddNewGroupAdapter groupsAdapter = new AddNewGroupAdapter(this);
    private Snackbar downloadingSnackbar;

    @BindView(R.id.addnewgroup_root)
    View root;

    @BindView(R.id.group_list)
    RecyclerView recyclerView;

    @BindView(R.id.search_edittext)
    EditText search_edittext;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    AddNewGroupPresenter presenter;

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

            }
        });
    }


    private void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();

            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    private Snackbar initSnackBar(String groupName) {
        return Snackbar.make(root, getString(R.string.Downloading_add_new_group, groupName)
                , Snackbar.LENGTH_INDEFINITE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showAvailibleGroups(List<String> groups) {
        groupsAdapter.setItems(groups);
    }

    @Override
    public void showErrorView(String errorName) {
        Snackbar.make(root, getString(R.string.error_add_new_group, errorName)
                , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showDownloading(String groups) {
        downloadingSnackbar = initSnackBar(groups);
        downloadingSnackbar.show();
    }

    @Override
    public void hideDownloading() {
        downloadingSnackbar.dismiss();
    }

    @Override
    public void addShortcut(String group) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = this.getSystemService(ShortcutManager.class);
            Intent intent = new Intent(Intent.ACTION_MAIN, Uri.EMPTY, this, ScheduleActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

            if (shortcutManager.getDynamicShortcuts().size() != 4) {
                ShortcutInfo webShortcut = new ShortcutInfo.Builder(this, group)
                        .setShortLabel(group)
                        .setLongLabel(group)
                        .setRank(shortcutManager.getDynamicShortcuts().size() + 1)
                        .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                        .setIntent(intent.putExtra("group", group))
                        .build();
                shortcutManager.addDynamicShortcuts(Arrays.asList(webShortcut));
            }
        }
    }

    @Override
    public void onGroupClickedListener(String groupName) {
        presenter.addNewGroup(groupName);
    }

    private void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(groupsAdapter);
    }
}
