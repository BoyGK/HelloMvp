package com.gkpoter.hellomvp.base;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


public abstract class BaseActivity extends AppCompatActivity
        implements IBaseView, View.OnClickListener {

    /**
     * 当前Activity渲染的view
     */
    protected View contentView;

    /**
     * 第一次点击事件记录
     */
    private long lastClick = 0;

    protected BaseActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        Bundle bundle = getIntent().getExtras();
        initData(bundle);
        setBaseView();
        initView(savedInstanceState, contentView);
        doBusiness(this);
    }

    protected void setBaseView() {
        contentView = LayoutInflater.from(this).inflate(bindLayout(), null);
        setContentView(contentView);
    }

    /**
     * 判断是否快速点击
     *
     * @return
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if(!isFastClick()) doWidgetClick(v);
    }
}
