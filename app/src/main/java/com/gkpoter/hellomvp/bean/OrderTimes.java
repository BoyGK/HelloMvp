package com.gkpoter.hellomvp.bean;

/**
 * Created by "GKpoter" on 2017/9/22.
 */

public class OrderTimes {
    private int state;
    private String msg;
    private String[] time;

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

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }
}
