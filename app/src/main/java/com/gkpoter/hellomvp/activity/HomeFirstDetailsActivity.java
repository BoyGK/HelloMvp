package com.gkpoter.hellomvp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.adapter.ItemDetailsAdapter;
import com.gkpoter.hellomvp.base.BaseActivity;
import com.gkpoter.hellomvp.bean.DetailsItemBean;

import java.util.ArrayList;
import java.util.List;

public class HomeFirstDetailsActivity extends BaseActivity {

    private Button add;
    private ListView listView;
    private ItemDetailsAdapter adapter = null;
    private List<DetailsItemBean> dates;

    @Override
    public void initData(Bundle bundle) {
        dates = new ArrayList<>();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_home_first_details;
    }

    @Override
    public void initView(Bundle bundle, View view) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.home_first_details_toolbar);
        toolbar.setTitle(getIntent().getStringExtra("title"));
        setSupportActionBar(toolbar);
        add = (Button) findViewById(R.id.details_add);
        add.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.details_list);
        listView.setOnItemClickListener(itemClickListener);
        listView.setOnItemLongClickListener(itemLongClickListener);
    }

    @Override
    public void doWidgetClick(View view) {
        if (view.equals(add)) {
            View v = LayoutInflater.from(
                    HomeFirstDetailsActivity.this).inflate(R.layout.dialog_1, null);
            final EditText text1 = (EditText) v.findViewById(R.id.text1);
            final EditText text2 = (EditText) v.findViewById(R.id.text2);
            new AlertDialog.Builder(HomeFirstDetailsActivity.this)
                    .setTitle("添加信息:")
                    .setView(v)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dates.add(new DetailsItemBean(
                                    "物品:" + text1.getText(), "单价:" + text2.getText()));
                            if (adapter != null) {
                                adapter.setTexts(dates);
                                adapter.notifyDataSetChanged();
                            } else {
                                adapter = new ItemDetailsAdapter(dates, getApplicationContext());
                                listView.setAdapter(adapter);
                            }
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }
    }

    @Override
    public void doBusiness(Context context) {

    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            System.out.println(position);
        }
    };

    AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            dates.remove(position);
            adapter.setTexts(dates);
            adapter.notifyDataSetChanged();
            return true;
        }
    };
}
