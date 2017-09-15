package com.gkpoter.hellomvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.adapter.ItemOrderAdapter;
import com.gkpoter.hellomvp.base.BaseActivity;
import com.gkpoter.hellomvp.bean.ItemOrderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by "GKpoter" on 2017/9/15.
 */

public class HistoryDetailsActivity extends BaseActivity {

    private ExpandableListView listView;
    private ItemOrderAdapter adapter;
    private List<ItemOrderBean> dates;

    @Override
    public void initData(Bundle bundle) {
        dates = new ArrayList<>();
        for(int i=0;i<15;i++) {
            List<ItemOrderBean.Child> childs = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                ItemOrderBean.Child child = new ItemOrderBean.Child(j + "", j * 10 + ".00", j * 10);
                childs.add(child);
            }
            dates.add(new ItemOrderBean("大叔阿萨德", 10, "2017/09/11", childs));
        }
        adapter = new ItemOrderAdapter(dates, getApplicationContext());
    }

    @Override
    public int bindLayout() {
        return R.layout.item_history_child;
    }

    @Override
    public void initView(Bundle bundle, View view) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.history_details);
        toolbar.setTitle(getIntent().getStringExtra("time"));
        setSupportActionBar(toolbar);
        listView = (ExpandableListView) view.findViewById(R.id.history_item_list);
        listView.setGroupIndicator(null);
        listView.setAdapter(adapter);
    }

    @Override
    public void doWidgetClick(View view) {

    }

    @Override
    public void doBusiness(Context context) {

    }
}
