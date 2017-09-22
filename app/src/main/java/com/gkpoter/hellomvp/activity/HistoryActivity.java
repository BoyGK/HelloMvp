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

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends BaseActivity {

    private ListView listView;
    private ItemHistoryAdapter adapter;
    private List<String> dates;

    @Override
    public void initData(Bundle bundle) {
        dates = new ArrayList<>();
        for (int k = 0; k < 15; k++) {
            dates.add("2017/06/28");
        }
        String[] str = new String[dates.size()];
        for (int i = 0, len = dates.size(); i < len; i++) {
            str[i] = dates.get(i);
        }
        adapter = new ItemHistoryAdapter(getApplicationContext(), str);
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
    public void doBusiness(final Context context) {
//        DataUtils dataUtils = new DataUtils("user", context);
//        HashMap<String, String> map = new HashMap<>();
//        map.put("username", dataUtils.getData("username", ""));
//        HttpUtils.Get("getorder", map, new MyCallBack<String>() {
//            @Override
//            public void onSuccess(String result) {
//                OrderTimes re = new Gson().fromJson(result, OrderTimes.class);
//                if (re.getState() != 0) {
//                    adapter = new ItemHistoryAdapter(context, re.getTime());
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
