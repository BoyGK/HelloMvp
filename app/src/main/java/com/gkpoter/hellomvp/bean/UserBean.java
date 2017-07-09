package com.gkpoter.hellomvp.bean;

/**
 * Created by "GKpoter" on 2017/7/3.
 */

public class UserBean {
    /**
     * ret : 0
     * msg : 登录成功
     * data : {"username":"admin","phone":"18847163389","ak":"5c2016efc3d17c558b4788a153d3084889148fbb83451fe596bbb231b65780c275bbaafe8f7964257caf0726f2047a49"}
     */

    private int ret;
    private String msg;
    private DataBean data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username : admin
         * phone : 18847163389
         * ak : 5c2016efc3d17c558b4788a153d3084889148fbb83451fe596bbb231b65780c275bbaafe8f7964257caf0726f2047a49
         */

        private String username;
        private String phone;
        private String ak;
        private String userPhoto;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAk() {
            return ak;
        }

        public void setAk(String ak) {
            this.ak = ak;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }
    }
}
