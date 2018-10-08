package com.mad.trafficclient.bean;

public class UserBean {
    private int id;

    //所有人
    private String carUseName;
    //图片
    private int img;
    //车辆型号
    private String carNum;
    //余额
    private String monery;

    private boolean flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarUseName() {
        return carUseName;
    }

    public void setCarUseName(String carUseName) {
        this.carUseName = carUseName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getMonery() {
        return monery;
    }

    public void setMonery(String monery) {
        this.monery = monery;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", carUseName='" + carUseName + '\'' +
                ", img=" + img +
                ", carNum='" + carNum + '\'' +
                ", monery='" + monery + '\'' +
                ", flag=" + flag +
                '}';
    }
}
