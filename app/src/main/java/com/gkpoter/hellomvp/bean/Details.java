package com.gkpoter.hellomvp.bean;

import java.util.List;

/**
 * Created by "GKpoter" on 2017/9/22.
 */

public class Details {
    private int state;
    private String msg;
    private List<DetailsItemBean> data;

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

    public List<DetailsItemBean> getData() {
        return data;
    }

    public void setData(List<DetailsItemBean> data) {
        this.data = data;
    }
}
