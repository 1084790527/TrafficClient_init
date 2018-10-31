package com.mad.trafficclient.bean;

public class F_10_xq_Bean {
    private String id;
    private String busId;
    private String num;

    @Override
    public String toString() {
        return "F_10_xq_Bean{" +
                "id='" + id + '\'' +
                ", busId='" + busId + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
