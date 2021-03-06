package com.gkpoter.hellomvp.fragment;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.activity.HomeFirstDetailsActivity;
import com.gkpoter.hellomvp.base.BaseFragment;
import com.gkpoter.hellomvp.bean.BaseBean;
import com.gkpoter.hellomvp.interface_.MyCallBack;
import com.gkpoter.hellomvp.util.DataUtils;
import com.gkpoter.hellomvp.util.HttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by "GKpoter" on 2017/7/10.
 */

public class HomeFirstFragment extends BaseFragment implements View.OnTouchListener {

    private FloatingActionButton add;

    private GridLayout gridLayout;
    private List<Button> buttons = new ArrayList<>();
    private float EV = 0;

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home_first;
    }

    @Override
    public void initView(Bundle bundle, View view) {
        add = (FloatingActionButton) view.findViewById(R.id.home_first_add);
        add.setOnClickListener(this);
        gridLayout = (GridLayout) view.findViewById(R.id.home_first_grid);
        for (int i = 0, len = gridLayout.getChildCount(); i < len; i++) {
            buttons.add((Button) gridLayout.getChildAt(i));
            gridLayout.getChildAt(i).setOnClickListener(this);
            gridLayout.getChildAt(i).setOnTouchListener(this);
        }
        EV = gridLayout.getChildAt(0).getElevation();
    }

    @Override
    public void doWidgetClick(View view) {
        if (view.equals(add)) {
            final EditText text = new EditText(mBaseActivity);
            new AlertDialog.Builder(mBaseActivity)
                    .setTitle("添加分类:")
                    .setView(text)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!"".equals(text.getText().toString())) {
                                addButton(text.getText().toString());
//                                addClass(text.getText().toString());
                            }
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();

        } else {
            Intent intent = new Intent(mBaseActivity, HomeFirstDetailsActivity.class);
            intent.putExtra("title", ((Button) view).getText());
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
                    mBaseActivity, view, "home2details").toBundle());
        }
    }

    private void addClass(String s) {
        DataUtils dataUtils = new DataUtils("user", getContext());
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", dataUtils.getData("username", ""));
        map.put("clazz", s);
        HttpUtils.Post("addclass", map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                BaseBean re = new Gson().fromJson(result, BaseBean.class);
                if (re.getState() != 1) {
                    Toast.makeText(mBaseActivity, re.getMsg() + "", Toast.LENGTH_SHORT).show();
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

    private void addButton(String text) {
        Button button = new Button(mBaseActivity);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1.0f);
        layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1.0f);
        layoutParams.setMargins(dp2px(mBaseActivity, 10), dp2px(mBaseActivity, 10),
                dp2px(mBaseActivity, 10), dp2px(mBaseActivity, 10));
        layoutParams.height = gridLayout.getChildAt(0).getLayoutParams().height;
        layoutParams.width = 0;
        button.setWidth(dp2px(mBaseActivity, 100));
        button.setText(text);
        button.setTextColor(Color.WHITE);
        button.setTextSize(22);
        button.setBackgroundColor(0xff000000 | new Random().nextInt(0xffffff));
        button.setOnClickListener(this);
        button.setOnTouchListener(this);
        gridLayout.addView(button, layoutParams);
        buttons.add(button);
    }

    @Override
    public void doBusiness(Context context) {
//        DataUtils dataUtils = new DataUtils("user", context);
//        HashMap<String, String> map = new HashMap<>();
//        map.put("username", dataUtils.getData("username", ""));
//        HttpUtils.Get("getclass", map, new MyCallBack<String>() {
//
//            @Override
//            public void onSuccess(String result) {
//                Classify re = new Gson().fromJson(result, Classify.class);
//                if (re.getState() != 0) {
//                    for (String s : re.getData()) {
//                        addButton(s);
//                    }
//                } else {
//                    Toast.makeText(mBaseActivity, re.getMsg() + "", Toast.LENGTH_SHORT).show();
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


    private int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                v.setElevation(25);
//                break;
//            case MotionEvent.ACTION_UP:
//                v.setElevation(EV);
//                break;
//            default:
////                v.setElevation(EV);
//                break;
//        }
        return false;
    }

}
