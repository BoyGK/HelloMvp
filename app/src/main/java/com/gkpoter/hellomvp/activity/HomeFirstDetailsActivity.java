package com.gkpoter.hellomvp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.adapter.ItemDetailsAdapter;
import com.gkpoter.hellomvp.base.BaseActivity;
import com.gkpoter.hellomvp.bean.BaseBean;
import com.gkpoter.hellomvp.bean.DetailsItemBean;
import com.gkpoter.hellomvp.interface_.MyCallBack;
import com.gkpoter.hellomvp.util.DataUtils;
import com.gkpoter.hellomvp.util.HttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFirstDetailsActivity extends BaseActivity {

    private Button add;
    private ListView listView;
    private ItemDetailsAdapter adapter = null;
    private List<DetailsItemBean> dates;
    private String clazz;

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
        clazz = getIntent().getStringExtra("title");
        toolbar.setTitle(clazz);
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
            final EditText text3 = (EditText) v.findViewById(R.id.text3);
            new AlertDialog.Builder(HomeFirstDetailsActivity.this)
                    .setTitle("添加信息:")
                    .setView(v)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!"".equals(text1.getText().toString()) &&
                                    !"".equals(text3.getText().toString()) &&
                                    !"".equals(text2.getText().toString())) {
                                dates.add(new DetailsItemBean()
                                        .setName(text1.getText().toString())
                                        .setPrice(Float.valueOf(text2.getText().toString()))
                                        .setUnit(text3.getText().toString()));
//                            addDetails(text1.getText().toString(),
//                                    Float.valueOf(text2.getText().toString()),
//                                    text3.getText().toString());
                                if (adapter != null) {
                                    adapter.setTexts(dates);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    adapter = new ItemDetailsAdapter(dates, getApplicationContext());
                                    listView.setAdapter(adapter);
                                }
                            }
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }
    }

    private void addDetails(String s, Float aFloat, String s1) {
        DataUtils dataUtils = new DataUtils("user", getApplicationContext());
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", dataUtils.getData("username", ""));
        map.put("name", s);
        map.put("unit", s1);
        map.put("price", aFloat);
        HttpUtils.Post("addclassdetails", map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                BaseBean re = new Gson().fromJson(result, BaseBean.class);
                if (re.getState() != 1) {
                    Toast.makeText(mActivity, re.getMsg() + "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void doBusiness(final Context context) {
//        DataUtils dataUtils = new DataUtils("user", context);
//        HashMap<String, String> map = new HashMap<>();
//        map.put("username", dataUtils.getData("username", ""));
//        map.put("clazz", clazz);
//        HttpUtils.Get("getclassdetails", map, new MyCallBack<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Details re = new Gson().fromJson(result, Details.class);
//                if (re.getState() != 0) {
//                    adapter = new ItemDetailsAdapter(re.getData(), context);
//                    listView.setAdapter(adapter);
//                } else {
//                    Toast.makeText(context, re.getMsg() + "", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
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
//            deleteClass(dates.get(position));
            dates.remove(position);
            adapter.setTexts(dates);
            adapter.notifyDataSetChanged();
            return true;
        }
    };

    private void deleteClass(DetailsItemBean detailsItemBean) {
        DataUtils dataUtils = new DataUtils("user", getApplicationContext());
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", dataUtils.getData("username", ""));
        map.put("name", detailsItemBean.getName());
        HttpUtils.Post("deleteclassdetails", map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                BaseBean re = new Gson().fromJson(result, BaseBean.class);
                if (re.getState() != 1) {
                    Toast.makeText(mActivity, re.getMsg() + "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
