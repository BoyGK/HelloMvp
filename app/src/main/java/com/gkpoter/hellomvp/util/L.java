package com.gkpoter.hellomvp.util;

import android.util.Log;

/**
 * Created by "GKpoter" on 2017/7/3.
 */

public class L {
    private static boolean key = true;

    public static void i(String msg) {
        if (key) {
            Log.i("myapp_informaton", msg);
        }
    }
}
