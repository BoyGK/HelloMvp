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
import com.gkpoter.hellomvp.bean.BaseBean;
import com.gkpoter.hellomvp.bean.UserBean;
import com.gkpoter.hellomvp.interface_.MyCallBack;
import com.gkpoter.hellomvp.util.DataUtils;
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
            DataUtils util = new DataUtils("user", getActivity());
            util.clearData();
            util.saveData("username", user.getUsername());
            util.saveData("password", user.getUsername());
            startActivity(new Intent(getActivity(), HomeActivity.class));
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
        HttpUtils.Post("login", map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                BaseBean re = new Gson().fromJson(result, BaseBean.class);
                if (re != null && re.getState() != 0) {
                    call.success(new UserBean().setUsername(nameOrphone.getText().toString())
                            .setPassword(password.getText().toString()));
                } else {
                    call.error(re.getMsg());
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
