package com.gkpoter.hellomvp.base;


import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.gkpoter.hellomvp.R;
import com.r0adkll.slidr.Slidr;

public abstract class BaseBackActivity extends BaseActivity {

    protected Toolbar mToolbar;

    @Override
    protected void setBaseView() {
        Slidr.attach(this);
        contentView = LayoutInflater.from(this).inflate(R.layout.activity_base_back, null);
        setContentView(contentView);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.content_view);
        frameLayout.addView(LayoutInflater.from(this).inflate(bindLayout(), frameLayout, false));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        contentView.setBackgroundColor(Color.WHITE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
