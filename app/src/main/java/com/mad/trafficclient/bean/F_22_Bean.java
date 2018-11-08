package com.mad.trafficclient.bean;

public class F_22_Bean {
    private int id;
    private int red;
    private int yellow;
    private int green;
    private boolean cb;

    @Override
    public String toString() {
        return "F_22_Bean{" +
                "id=" + id +
                ", red=" + red +
                ", yellow=" + yellow +
                ", green=" + green +
                ", cb=" + cb +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public boolean isCb() {
        return cb;
    }

    public void setCb(boolean cb) {
        this.cb = cb;
    }
}
