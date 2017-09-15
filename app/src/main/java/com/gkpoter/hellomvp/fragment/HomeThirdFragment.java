package com.gkpoter.hellomvp.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.activity.HistoryActivity;
import com.gkpoter.hellomvp.base.BaseFragment;
import com.gkpoter.hellomvp.view.GraphView;

/**
 * Created by "GKpoter" on 2017/7/10.
 */

public class HomeThirdFragment extends BaseFragment {

    private Button history;
    private FloatingActionButton up;
    private GraphView graphView;

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home_third;
    }

    @Override
    public void initView(Bundle bundle, View view) {
        history = (Button) view.findViewById(R.id.home_third_history);
        up = (FloatingActionButton) view.findViewById(R.id.home_third_up);
        graphView = (GraphView) view.findViewById(R.id.graph);
        history.setOnClickListener(this);
        up.setOnClickListener(this);
    }

    @Override
    public void doWidgetClick(View view) {
        if (view.equals(up)) {
            Snackbar.make(view, "上传本次订单", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mBaseActivity, "上传成功，可在历史订单中查看。", Toast.LENGTH_SHORT).show();
                }
            }).show();
        } else {
            startActivity(new Intent(mBaseActivity, HistoryActivity.class));
        }
    }

    @Override
    public void doBusiness(Context context) {
        graphView.setWidth(31);
        graphView.setHeight(2000);
        graphView.drawGraph(
                new Point(4, 1200),
                new Point(10, 880),
                new Point(18, 1000),
                new Point(23, 1200),
                new Point(28, 1100));
    }
}
