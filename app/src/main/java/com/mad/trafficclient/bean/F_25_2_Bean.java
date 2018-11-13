package com.mad.trafficclient.bean;

public class F_25_2_Bean {
    private String date;
    private String week;
    private String czr;
    private String cph;
    private int cz;
    private int ye;
    private String czdate;

    @Override
    public String toString() {
        return "F_25_2_Bean{" +
                "date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", czr='" + czr + '\'' +
                ", cph='" + cph + '\'' +
                ", cz=" + cz +
                ", ye=" + ye +
                ", czdate='" + czdate + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public int getCz() {
        return cz;
    }

    public void setCz(int cz) {
        this.cz = cz;
    }

    public int getYe() {
        return ye;
    }

    public void setYe(int ye) {
        this.ye = ye;
    }

    public String getCzdate() {
        return czdate;
    }

    public void setCzdate(String czdate) {
        this.czdate = czdate;
    }
}
