package com.gkpoter.hellomvp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.adapter.ItemOrderAdapter;
import com.gkpoter.hellomvp.base.BaseFragment;
import com.gkpoter.hellomvp.bean.ItemOrderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by "GKpoter" on 2017/7/10.
 */

public class HomeSecondFragment extends BaseFragment {

    private ExpandableListView listView;
    private ItemOrderAdapter adapter;
    private List<ItemOrderBean> dates;

    @Override
    public void initData(Bundle bundle) {
        dates = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<ItemOrderBean.Child> childs = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                ItemOrderBean.Child child = new ItemOrderBean.Child(j + "", j * 10 + ".00", j * 10);
                childs.add(child);
            }
            dates.add(new ItemOrderBean("大叔阿萨德", i * 10, "2017/09/11", childs));
        }
        adapter = new ItemOrderAdapter(dates, getActivity());
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home_second;
    }

    @Override
    public void initView(Bundle bundle, View view) {
        listView = (ExpandableListView) view.findViewById(R.id.order_list);
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
