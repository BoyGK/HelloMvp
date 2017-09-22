package com.gkpoter.hellomvp.bean;

/**
 * Created by "GKpoter" on 2017/7/3.
 */

public class UserBean {


    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public UserBean setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserBean setPassword(String password) {
        this.password = password;
        return this;
    }
}
