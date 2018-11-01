package com.mad.trafficclient.bean;

public class F_12_Bean {
    private int icon;
    private String license;
    private String balance;

    @Override
    public String toString() {
        return "F_12_Bean{" +
                "icon=" + icon +
                ", license='" + license + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
