package com.huihuijiang.tool;

/**
 * Created by fengzhiqi on 2017/6/10.
 */

public class RadarData {

    private String title;
    private double percentage;


    public RadarData(String title, double percentage) {
        this.title = title;
        this.percentage = percentage;
    }


    public String getTitle() {
        return title;
    }


    public double getPercentage() {
        return percentage;
    }
}
