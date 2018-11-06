package com.mad.trafficclient.bean;

import java.util.Comparator;

public class F_14_Bean implements Comparable<F_14_Bean>{
    private int id;
    private int money;
    private Boolean t;

    @Override
    public String toString() {
        return "F_14_Bean{" +
                "id=" + id +
                ", money=" + money +
                ", t=" + t +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Boolean getT() {
        return t;
    }

    public void setT(Boolean t) {
        this.t = t;
    }

    @Override
    public int compareTo(F_14_Bean f_14_bean) {
        if (this.id>f_14_bean.getId()){
            return 1;
        }
        return -1;
    }
}
