package com.example.sunny.gogleplay.bean;

/**
 * ============================
 *
 * @Created by sunny
 * @project com.example.sunny.gogleplay.bean
 * @file GoglePlay
 * @CreatedTime 2018/6/12 14:50
 * @Desc 浅谈宣泄 深水无波
 */

public class AppInfo {
    public long id;
    public String name;
    public String packageName;
    public String iconUrl;
    public double stars;
    public long size;
    public String downloadUrl;
    public String des;

    public AppInfo(long id, String name, String packageName, String iconUrl, double stars, long size, String downloadUrl, String des) {
        this.id = id;
        this.name = name;
        this.packageName = packageName;
        this.iconUrl = iconUrl;
        this.stars = stars;
        this.size = size;
        this.downloadUrl = downloadUrl;
        this.des = des;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", stars=" + stars +
                ", size=" + size +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
