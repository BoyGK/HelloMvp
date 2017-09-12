package com.gkpoter.hellomvp.bean;

import android.content.Intent;

import java.util.List;

/**
 * Created by "GKpoter" on 2017/9/10.
 */

public class ItemOrderBean {

    private String parent;
    private Integer sum;
    private String time;
    private List<Child> childs;


    public ItemOrderBean(String parent, Integer sum, String time, List<Child> childs) {
        this.parent = parent;
        this.sum = sum;
        this.time = time;
        this.childs = childs;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public List<Child> getChilds() {
        return childs;
    }

    public void setChilds(List<Child> childs) {
        this.childs = childs;
    }

    public static class Child {
        private String name;
        private String price;
        private Integer num;

        public Child(String name, String price, Integer num) {
            this.name = name;
            this.price = price;
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }
    }
}
