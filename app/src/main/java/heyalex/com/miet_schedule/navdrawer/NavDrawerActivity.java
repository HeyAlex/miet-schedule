package heyalex.com.miet_schedule.navdrawer;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import heyalex.com.miet_schedule.R;

/**
 * Created by alexf on 04.04.2017.
 */

public class NavDrawerActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.rv_drawer_recycler)
    RecyclerView mRecyclerView;

    @Nullable
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;


    private static final int NAV_DRAWER_NO_ITEM = -1;

    @Inject
    NavDrawerPresenter navDrawerPresenter;

    @IdRes
    protected int getNavDrawerItemId(){
        return NAV_DRAWER_NO_ITEM;
    }

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


}
