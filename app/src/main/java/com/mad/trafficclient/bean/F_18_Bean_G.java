package com.mad.trafficclient.bean;

import java.util.Collections;
import java.util.List;

public class F_18_Bean_G implements Comparable<F_18_Bean_G> {
    private int title;
    private List<F_18_Bean_C> list;

    @Override
    public String toString() {
        return "F_18_Bean_G{" +
                "title=" + title +
                ", list=" + list +
                '}';
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public List<F_18_Bean_C> getList() {
        return list;
    }

    public void setList(List<F_18_Bean_C> list) {
        this.list = list;
    }

    @Override
    public int compareTo(F_18_Bean_G f_18_bean_g) {
        if (this.title>f_18_bean_g.title){
            return 1;
        }
        return -1;
    }
}
