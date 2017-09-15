package com.gkpoter.hellomvp.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.adapter.ItemHistoryAdapter;
import com.gkpoter.hellomvp.base.BaseActivity;
import com.gkpoter.hellomvp.bean.HistoryBean;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends BaseActivity {

    private ListView listView;
    private ItemHistoryAdapter adapter;
    private List<HistoryBean> dates;

    @Override
    public void initData(Bundle bundle) {
        dates = new ArrayList<>();
        for (int k = 0; k < 15; k++) {
            dates.add(new HistoryBean("2017/06/28" + k));
        }
        adapter = new ItemHistoryAdapter(getApplicationContext(), dates);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_history;
    }

    @Override
    public void initView(Bundle bundle, View view) {
        listView = (ListView) findViewById(R.id.history_list);
        listView.setOnItemClickListener(listener);
        listView.setAdapter(adapter);
    }

    @Override
    public void doWidgetClick(View view) {

    }

    @Override
    public void doBusiness(Context context) {

    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), HistoryDetailsActivity.class);
            intent.putExtra("time", "2017/06/28");
            startActivity(intent, ActivityOptions
                    .makeSceneTransitionAnimation(HistoryActivity.this, view, "").toBundle());
        }
    };
}
