package com.mad.trafficclient.bean;

public class BusBean {
    private String busId;
    private String time;
    private String distance;

    @Override
    public String toString() {
        return "BusBean{" +
                "busId='" + busId + '\'' +
                ", time='" + time + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
