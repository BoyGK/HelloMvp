package com.gkpoter.hellomvp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.base.BaseActivity;
import com.gkpoter.hellomvp.fragment.HomeFirstFragment;
import com.gkpoter.hellomvp.fragment.HomeSecondFragment;
import com.gkpoter.hellomvp.fragment.HomeThirdFragment;
import com.gkpoter.hellomvp.util.FinishListActivity;

/**
 * Created by "GKpoter" on 2017/7/9.
 */

public class HomeActivity extends BaseActivity {

    private Fragment[] fragments;
    private FragmentPagerAdapter adapter;
    private FragmentManager fragmentManager;

    private ViewPager viewPager;

    @Override
    public void initData(Bundle bundle) {
        FinishListActivity.getInstance().addActivity(this);
        fragments = new Fragment[3];
        fragmentManager = getSupportFragmentManager();
        fragments[0] = new HomeFirstFragment();
        fragments[1] = new HomeSecondFragment();
        fragments[2] = new HomeThirdFragment();
        adapter = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                return fragments[i];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        };
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initView(Bundle bundle, View view) {
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_home);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(mListener);

        viewPager = (ViewPager) findViewById(R.id.home_viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOnTouchListener(touchListener);

        BottomNavigationView bottom_nav = (BottomNavigationView) findViewById(R.id.home_bottom_select_menu);
        bottom_nav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    @Override
    public void doWidgetClick(View view) {

    }

    @Override
    public void doBusiness(Context context) {

    }

    NavigationView.OnNavigationItemSelectedListener mListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_setting:
                    break;
                case R.id.action_theme:
                    break;
            }
            return false;
        }
    };

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    };

    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            viewPager.setCurrentItem(item.getGroupId());
            return true;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            FinishListActivity.getInstance().exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
