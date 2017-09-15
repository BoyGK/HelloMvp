package com.gkpoter.hellomvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.base.BaseActivity;
import com.gkpoter.hellomvp.fragment.LoginLeftFragment;
import com.gkpoter.hellomvp.fragment.LoginRightFragment;
import com.gkpoter.hellomvp.util.FinishListActivity;

/**
 * Created by "GKpoter" on 2017/7/9.
 */

public class LoginActivity extends BaseActivity {

    private Fragment[] fragments;
    private FragmentPagerAdapter adapter;
    private FragmentManager fragmentManager;

    @Override
    public void initData(Bundle bundle) {
        FinishListActivity.getInstance().addActivity(this);
        fragments = new Fragment[2];
        fragmentManager = getSupportFragmentManager();
        fragments[0] = new LoginLeftFragment();
        fragments[1] = new LoginRightFragment();
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
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle bundle, View view) {
        Toolbar toolbar= (Toolbar) findViewById(R.id.login_toolbar);
        toolbar.setTitle(getString(R.string.actionbar_title));
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.login_viewPager);
        TabLayout tab = (TabLayout) findViewById(R.id.login_tab);

        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        tab.getTabAt(0).setText("账号密码登录");
        tab.getTabAt(1).setText("手机号登录");
        viewPager.setOnTouchListener(touchListener);
    }

    @Override
    public void doWidgetClick(View view) {

    }

    @Override
    public void doBusiness(Context context) {

    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        FinishListActivity.getInstance().exit();
    }
}
