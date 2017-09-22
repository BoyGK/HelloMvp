package com.gkpoter.hellomvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.bean.DetailsItemBean;

import java.util.List;

/**
 * Created by "GKpoter" on 2017/9/10.
 */

public class ItemDetailsAdapter extends BaseAdapter {

    private List<DetailsItemBean> texts;
    private Context context;

    public void setTexts(List<DetailsItemBean> texts) {
        this.texts = texts;
    }

    public ItemDetailsAdapter(List<DetailsItemBean> texts, Context context) {
        this.texts = texts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return texts.size();
    }

    @Override
    public Object getItem(int position) {
        return texts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_details,null);
            viewHolder = new ViewHolder();
            /**
             * 添加子view
             */
            viewHolder.text1 = (TextView) convertView.findViewById(R.id.item_text1);
            viewHolder.text2 = (TextView) convertView.findViewById(R.id.item_text2);
            viewHolder.text3 = (TextView) convertView.findViewById(R.id.unit);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text1.setText("物品:" + texts.get(position).getName());
        viewHolder.text2.setText("单价:" + texts.get(position).getPrice().toString());
        viewHolder.text3.setText(" " + texts.get(position).getUnit());

        return convertView;
    }

    private class ViewHolder{
        public TextView text1;
        public TextView text2;
        public TextView text3;
    }
}
