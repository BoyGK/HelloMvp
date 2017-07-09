package com.gkpoter.hellomvp.app;

import android.app.Application;

import org.xutils.x;

/**
 * Created by "GKpoter" on 2017/7/3.
 */

public class MyAppLocation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
