package com.gkpoter.hellomvp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by "GKpoter" on 2017/7/9.
 */

interface IBaseView {

    /**
     * 初始化数据
     *
     * @param bundle 传功来的数据
     */
    void initData(Bundle bundle);

    /**
     *
     * @return 布局id
     */
    int bindLayout();

    /**
     * 初始化view
     *
     * @param bundle
     * @param view
     */
    void initView(Bundle bundle, View view);

    /**
     * 控件响应事件
     *
     * @param view
     */
    void doWidgetClick(View view);

    /**
     * 逻辑事物处理
     *
     * @param context
     */
    void doBusiness(Context context);

}
