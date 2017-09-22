package com.gkpoter.hellomvp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.activity.HomeActivity;
import com.gkpoter.hellomvp.activity.RegisterActivity;
import com.gkpoter.hellomvp.base.BaseFragment;
import com.gkpoter.hellomvp.bean.BaseBean;
import com.gkpoter.hellomvp.bean.UserBean;
import com.gkpoter.hellomvp.interface_.MyCallBack;
import com.gkpoter.hellomvp.util.DataUtils;
import com.gkpoter.hellomvp.util.HttpUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;

import java.util.HashMap;

/**
 * Created by "GKpoter" on 2017/7/9.
 */

public class LoginRightFragment extends BaseFragment {

    private EditText phone;
    private EditText code;

    private LoginCall call = new LoginCall() {
        @Override
        public void success(UserBean user) {
            DataUtils util = new DataUtils("userbean", getActivity());
//            util.saveData("username", user.getData().getUsername());
//            util.saveData("phone", user.getData().getPhone());
//            util.saveData("password", user.getData().getUsername());
//            util.saveData("ak", user.getData().getAk());
//            util.saveData("userPhoto", user.getData().getUserPhoto());
//            startActivity(new Intent(getActivity(), HomeActivity.class));
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
        return R.layout.fragment_login_right;
    }

    @Override
    public void initView(Bundle bundle, View view) {
        view.findViewById(R.id.login_SignUp).setOnClickListener(this);
        view.findViewById(R.id.login_getCode).setOnClickListener(this);
        view.findViewById(R.id.login_SIgnIn).setOnClickListener(this);
        phone = (EditText) view.findViewById(R.id.login_NameOrPhone);
        code = (EditText) view.findViewById(R.id.login_code);
    }

    @Override
    public void doWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.login_SignUp:
                doRegister();
                break;
            case R.id.login_getCode:
                requestCode();
                break;
            case R.id.login_SIgnIn:
                doLogin();
                break;
        }
    }

    @Override
    public void doBusiness(Context context) {

    }

    private void doLogin() {
        if (TextUtils.isEmpty(phone.getText())) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage("手机号不能为空")
                    .setPositiveButton("确定", null)
                    .create();
            alertDialog.show();
            return;
        }
        if (TextUtils.isEmpty(code.getText())) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage("验证码不能为空")
                    .setPositiveButton("确定", null)
                    .create();
            alertDialog.show();
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", phone.getText().toString());
        map.put("code", code.getText().toString());
        HttpUtils.Post("v1/market/login_by_phone", map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
//                UserBean user = new Gson().fromJson(result, UserBean.class);
//                if (user != null && user.getRet() != 0) {
//                    call.error(user.getMsg());
//                } else {
//                    call.success(user);
//                }
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

    //获取验证码
    private void requestCode() {
        if (TextUtils.isEmpty(phone.getText())) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage("手机号不能为空")
                    .setPositiveButton("确定", null)
                    .create();
            alertDialog.show();
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone.getText().toString());
        HttpUtils.Get("v1/market/send_sms", map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                BaseBean bean = new Gson().fromJson(result, BaseBean.class);
                if (bean != null && bean.getState() == 0) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("提示")
                            .setMessage("发送成功")
                            .setPositiveButton("确定", null)
                            .create();
                    alertDialog.show();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("提示")
                            .setMessage("发送失败，发送的频率太快")
                            .setPositiveButton("确定", null)
                            .create();
                    alertDialog.show();
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

    //go注册
    private void doRegister() {
        startActivity(new Intent(getActivity(), RegisterActivity.class));
    }

    //手机号登录回调接口
    private interface LoginCall {
        void success(UserBean user);

        void error(String msg);
    }
}
