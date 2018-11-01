package com.mad.trafficclient.bean;

public class F_13_Bean {
    private int id;
    private int red;
    private int yellow;
    private int green;

    @Override
    public String toString() {
        return "F_13_Bean{" +
                "id=" + id +
                ", red=" + red +
                ", yellow=" + yellow +
                ", green=" + green +
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
}
