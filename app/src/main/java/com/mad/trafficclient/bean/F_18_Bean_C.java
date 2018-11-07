package com.mad.trafficclient.bean;

public class F_18_Bean_C implements Comparable<F_18_Bean_C> {
    private int t;
    private int s;

    @Override
    public String toString() {
        return "F_18_Bean_C{" +
                "t=" + t +
                ", s=" + s +
                '}';
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    @Override
    public int compareTo(F_18_Bean_C f_18_bean_c) {
        if (this.s>f_18_bean_c.getS()){
            return 1;
        }
        return -1;
    }
}
