package heyalex.com.miet_schedule.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import heyalex.com.miet_schedule.R;

/**
 * Created by alexf on 06.04.2017.
 */

public abstract class BaseNavigationActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @Nullable
    @BindView(R.id.rv_drawer_recycler)
    public RecyclerView mRecyclerView;

    @Nullable
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;

    protected abstract void setupNavListView();

    protected boolean hasParentActivity(){
        return false;
    }

    protected void setupToolbar(){
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();

            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

        setupNavDrawer();
        setupToolbar();
    }


    @Override
    public void onBackPressed(){
        if (isNavDrawerOpened()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private boolean isNavDrawerOpened(){
        /**
         * Simplified from
         * {@code   if (drawerLayout != null) {
         *              return drawerLayout.isDrawerOpen(GravityCompat.START);
         *          } else {
         *               return false;
         *          }
         * }
         */
        return drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    private void closeNavDrawer(){
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (drawerToggle != null) {
            drawerToggle.syncState();
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (drawerToggle != null) {
            drawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


    private void setupNavDrawer(){
        if (drawerLayout != null &&  toolbar != null) {
            drawerToggle = new ActionBarDrawerToggle(this,
                    drawerLayout,
                    toolbar,
                    R.string.nav_drawer_open,
                    R.string.nav_drawer_close);

            drawerLayout.addDrawerListener(drawerToggle);

            setupNavListView();
        }
    }
}
