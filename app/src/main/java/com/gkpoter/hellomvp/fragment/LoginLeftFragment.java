package com.gkpoter.hellomvp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.activity.HomeActivity;
import com.gkpoter.hellomvp.activity.RegisterActivity;
import com.gkpoter.hellomvp.base.BaseFragment;
import com.gkpoter.hellomvp.bean.UserBean;
import com.gkpoter.hellomvp.interface_.MyCallBack;
import com.gkpoter.hellomvp.util.DataUtils;
import com.gkpoter.hellomvp.util.FinishListActivity;
import com.gkpoter.hellomvp.util.HttpUtils;
import com.gkpoter.hellomvp.util.L;
import com.google.gson.Gson;

import org.xutils.common.Callback;

import java.util.HashMap;

/**
 * Created by "GKpoter" on 2017/7/9.
 */

public class LoginLeftFragment extends BaseFragment {

    private EditText nameOrphone;
    private EditText password;

    /**
     * 登陆回调
     */
    private LoginCall call = new LoginCall() {
        @Override
        public void success(UserBean user) {
            L.i(user.getData().getAk());
            DataUtils util = new DataUtils("userbean", getActivity());
            util.clearData();
            util.saveData("username", user.getData().getUsername());
            util.saveData("phone", user.getData().getPhone());
            util.saveData("password", user.getData().getUsername());
            util.saveData("ak", user.getData().getAk());
            util.saveData("userPhoto", user.getData().getUserPhoto());
            startActivity(new Intent(getActivity(), HomeActivity.class));
            //getActivity().finish();
        }

        @Override
        public void error(String msg) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage(msg)
                    .setPositiveButton("确定", null)
                    .create();
            alertDialog.show();
        }
    };

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_login_left;
    }

    @Override
    public void initView(Bundle bundle, View view) {
        view.findViewById(R.id.login_SIgnIn).setOnClickListener(this);
        view.findViewById(R.id.login_SignUp).setOnClickListener(this);
        nameOrphone = (EditText) view.findViewById(R.id.login_NameOrPhone);
        password = (EditText) view.findViewById(R.id.login_PassWord);
    }

    @Override
    public void doWidgetClick(View view) {
        L.i("...................");
        switch (view.getId()) {
            case R.id.login_SIgnIn:
                //doLogin();
                startActivity(new Intent(getActivity(), HomeActivity.class));
                break;
            case R.id.login_SignUp:
                doRegister();
                break;
        }
    }

    @Override
    public void doBusiness(Context context) {

    }

    private void doRegister() {
        startActivity(new Intent(getActivity(), RegisterActivity.class));
    }

    private void doLogin() {
        if (TextUtils.isEmpty(nameOrphone.getText())) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage("用户名或手机号不能为空")
                    .setPositiveButton("确定", null)
                    .create();
            alertDialog.show();
            return;
        }
        if (TextUtils.isEmpty(password.getText())) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage("密码不能为空")
                    .setPositiveButton("确定", null)
                    .create();
            alertDialog.show();
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", nameOrphone.getText().toString());
        map.put("password", password.getText().toString());
        HttpUtils.Post("v1/market/login", map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                UserBean user = new Gson().fromJson(result, UserBean.class);
                if (user != null && user.getRet() != 0) {
                    call.error(user.getMsg());
                } else {
                    call.success(user);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //登陆回调接口
    private interface LoginCall {
        void success(UserBean user);

        void error(String msg);
    }
}
