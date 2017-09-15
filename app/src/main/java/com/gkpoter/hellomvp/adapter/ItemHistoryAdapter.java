package com.gkpoter.hellomvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.bean.HistoryBean;

import java.util.List;

/**
 * Created by "GKpoter" on 2017/9/15.
 */

public class ItemHistoryAdapter extends BaseAdapter {

    private Context context;
    private List<HistoryBean> dates;

    public ItemHistoryAdapter(Context context, List<HistoryBean> datas) {
        this.context = context;
        this.dates = datas;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_history_parent, null);
            viewHolder = new ViewHolder();
            /**
             * 添加子view
             */
            viewHolder.time = (TextView) view.findViewById(R.id.history_item_time);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.time.setText("订单日期:" + dates.get(position).getTime());
        return view;
    }

    private class ViewHolder {
        public TextView time;
    }

}
