package com.gkpoter.hellomvp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gkpoter.hellomvp.R;
import com.gkpoter.hellomvp.base.BaseActivity;
import com.gkpoter.hellomvp.bean.BaseBean;
import com.gkpoter.hellomvp.bean.UserBean;
import com.gkpoter.hellomvp.interface_.MyCallBack;
import com.gkpoter.hellomvp.util.FinishListActivity;
import com.gkpoter.hellomvp.util.HttpUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;

import java.util.HashMap;

/**
 * Created by "GKpoter" on 2017/7/9.
 */

public class RegisterActivity extends BaseActivity {

    private EditText nameOrphone;
    private EditText password;
    private EditText code;

    private RegisterCall call = new RegisterCall() {
        @Override
        public void success() {
            finish();
        }

        @Override
        public void error(String msg) {
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("提示")
                    .setMessage(msg)
                    .setPositiveButton("确定", null)
                    .create();
            alertDialog.show();
        }
    };

    @Override
    public void initData(Bundle bundle) {
        FinishListActivity.getInstance().addActivity(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(Bundle bundle, View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.register_toolbar);
        toolbar.setTitle(getString(R.string.actionbar_title));
        setSupportActionBar(toolbar);

        view.findViewById(R.id.register_getCode).setOnClickListener(this);
        view.findViewById(R.id.register_SignIn).setOnClickListener(this);
        nameOrphone = (EditText) view.findViewById(R.id.register_NameOrPhone);
        code = (EditText) view.findViewById(R.id.register_code);
        password = (EditText) view.findViewById(R.id.register_PassWord);
    }

    @Override
    public void doWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.register_getCode:
                requestCode();
                break;
            case R.id.register_SignIn:
                doRegister();
                break;
        }
    }

    @Override
    public void doBusiness(Context context) {

    }

    //获取验证码
    private void requestCode() {
        if (TextUtils.isEmpty(nameOrphone.getText())) {
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("提示")
                    .setMessage("手机号不能为空")
                    .setPositiveButton("确定", null)
                    .create();
            alertDialog.show();
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", nameOrphone.getText().toString());
        HttpUtils.Get("v1/market/send_sms", map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                BaseBean bean = new Gson().fromJson(result, BaseBean.class);
                if (bean != null && bean.getState() == 0) {
                    AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("提示")
                            .setMessage("发送成功")
                            .setPositiveButton("确定", null)
                            .create();
                    alertDialog.show();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this)
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

    //注册
    private void doRegister() {
        if (TextUtils.isEmpty(nameOrphone.getText())) {
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("提示")
                    .setMessage("手机号不能为空")
                    .setPositiveButton("确定", null)
                    .create();
            alertDialog.show();
            return;
        }
        if (TextUtils.isEmpty(password.getText())) {
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("提示")
                    .setMessage("密码不能为空")
                    .setPositiveButton("确定", null)
                    .create();
            alertDialog.show();
            return;
        }
        if (TextUtils.isEmpty(code.getText())) {
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("提示")
                    .setMessage("店铺名称不能为空")
                    .setPositiveButton("确定", null)
                    .create();
            alertDialog.show();
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", nameOrphone.getText().toString());
        map.put("password", password.getText().toString());
        map.put("code", code.getText().toString());
        HttpUtils.Post("v1/market/register_by_phone", map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
//                UserBean user = new Gson().fromJson(result, UserBean.class);
//                if (user != null && user.get() != 0) {
//                    call.error(user.getMsg());
//                } else {
//                    call.success();
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

    //注册回调接口
    private interface RegisterCall {
        void success();

        void error(String msg);
    }
}
