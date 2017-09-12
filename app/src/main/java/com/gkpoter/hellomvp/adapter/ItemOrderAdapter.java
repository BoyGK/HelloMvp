package com.gkpoter.hellomvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.bean.ItemOrderBean;

import java.util.List;

/**
 * Created by "GKpoter" on 2017/9/10.
 */

public class ItemOrderAdapter extends BaseExpandableListAdapter {

    private List<ItemOrderBean> dates;
    private Context context;

    public ItemOrderAdapter(List<ItemOrderBean> dates, Context context) {
        this.dates = dates;
        this.context = context;
    }

    public void setDates(List<ItemOrderBean> dates) {
        this.dates = dates;
    }

    @Override
    public int getGroupCount() {
        return dates.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dates.get(groupPosition).getChilds().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dates.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dates.get(groupPosition).getChilds().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        ParentHolder parentHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_parent, null);
            parentHolder = new ParentHolder();
            /**
             * 添加子view
             */
            parentHolder.name = (TextView) view.findViewById(R.id.name);
            parentHolder.sum = (TextView) view.findViewById(R.id.sum);
            parentHolder.time = (TextView) view.findViewById(R.id.time);
            view.setTag(parentHolder);
        } else {
            parentHolder = (ParentHolder) view.getTag();
        }
        parentHolder.name.setText("商家:" + dates.get(groupPosition).getParent());
        parentHolder.sum.setText("总价格:" + dates.get(groupPosition).getSum());
        parentHolder.time.setText("订单日期:" + dates.get(groupPosition).getTime());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_child, null);
            childHolder = new ChildHolder();
            /**
             * 添加子view
             */
            childHolder.name = (TextView) view.findViewById(R.id.name);
            childHolder.price = (TextView) view.findViewById(R.id.price);
            childHolder.num = (TextView) view.findViewById(R.id.num);
            view.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) view.getTag();
        }
        childHolder.name.setText("物品:" + dates.get(groupPosition).getChilds().get(childPosition).getName());
        childHolder.price.setText("单价:" + dates.get(groupPosition).getChilds().get(childPosition).getPrice());
        childHolder.num.setText("数量:" + dates.get(groupPosition).getChilds().get(childPosition).getNum());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ParentHolder {
        public TextView name;
        public TextView sum;
        public TextView time;
    }

    class ChildHolder {
        public TextView name;
        public TextView price;
        public TextView num;
    }
}
