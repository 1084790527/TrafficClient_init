package com.mad.trafficclient.bean;

public class F_11_Bean {
    private int icon;
    private String title;
    private String qd;
    private String xx;

    @Override
    public String toString() {
        return "F_10_Bean{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", qd='" + qd + '\'' +
                ", xx='" + xx + '\'' +
                '}';
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQd() {
        return qd;
    }

    public void setQd(String qd) {
        this.qd = qd;
    }

    public String getXx() {
        return xx;
    }

    public void setXx(String xx) {
        this.xx = xx;
    }
}
