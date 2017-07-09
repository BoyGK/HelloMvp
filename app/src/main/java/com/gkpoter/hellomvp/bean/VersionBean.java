package com.gkpoter.hellomvp.bean;

/**
 * Created by "GKpoter" on 2017/7/3.
 */

public class VersionBean {
    /**
     * ret : 0
     * msg : 检查成功
     * data : {"id":1,"versionCode":1,"versionName":"1.0","url":"http://www.baidu.com/1.apk","forced":1,"versionDescription":"此版本修复了一些bug","addTime":"2017-07-01 11:44:01"}
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
         * id : 1
         * versionCode : 1
         * versionName : 1.0
         * url : http://www.baidu.com/1.apk
         * forced : 1
         * versionDescription : 此版本修复了一些bug
         * addTime : 2017-07-01 11:44:01
         */

        private int id;
        private int versionCode;
        private String versionName;
        private String url;
        private int forced;
        private String versionDescription;
        private String addTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getForced() {
            return forced;
        }

        public void setForced(int forced) {
            this.forced = forced;
        }

        public String getVersionDescription() {
            return versionDescription;
        }

        public void setVersionDescription(String versionDescription) {
            this.versionDescription = versionDescription;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }
    }
}
