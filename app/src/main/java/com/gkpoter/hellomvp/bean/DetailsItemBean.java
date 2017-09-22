package com.gkpoter.hellomvp.bean;

/**
 * Created by "GKpoter" on 2017/9/10.
 */

public class DetailsItemBean {

    private String name;
    private Float price;
    private String unit;

    public String getName() {
        return name;
    }

    public DetailsItemBean setName(String name) {
        this.name = name;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public DetailsItemBean setPrice(Float price) {
        this.price = price;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public DetailsItemBean setUnit(String unit) {
        this.unit = unit;
        return this;
    }
}
