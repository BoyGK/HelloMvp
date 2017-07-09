package com.gkpoter.hellomvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.base.BaseActivity;
import com.gkpoter.hellomvp.util.FinishListActivity;

/**
 * Created by "GKpoter" on 2017/7/9.
 */

public class HomeActivity extends BaseActivity {



    @Override
    public void initData(Bundle bundle) {
        FinishListActivity.getInstance().exit();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initView(Bundle bundle, View view) {

    }

    @Override
    public void doWidgetClick(View view) {

    }

    @Override
    public void doBusiness(Context context) {

    }
}
