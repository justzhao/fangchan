package com.zhaopeng.fangchan.entity;

import com.zhaopeng.fangchan.util.DateUtil;

import java.io.File;
import java.util.Date;

/**
 * Created by zhaopeng on 2017/9/16.
 */
public class EstateInfo {

    private String content;

    private String city;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String  getDir() {
        String dir = city + File.separator + DateUtil.getDay(new Date())+File.separator+"info.csv";
        return dir;
    }
}
