package com.gkpoter.hellomvp.bean;

/**
 * Created by "GKpoter" on 2017/9/22.
 */

public class Classify {

    private int state;
    private String msg;
    private String[] data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
