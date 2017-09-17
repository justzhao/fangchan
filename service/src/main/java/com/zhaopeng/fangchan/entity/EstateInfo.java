package com.zhaopeng.fangchan.entity;

import com.zhaopeng.fangchan.store.StoreConfig;
import com.zhaopeng.fangchan.util.DateUtil;

import java.io.File;
import java.util.Date;

/**
 * Created by zhaopeng on 2017/9/16.
 */
public class EstateInfo {

    private String content;

    private String city;

    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDir() {
        String dir = StoreConfig.storePathRootDir + File.separator + city + File.separator + type + File.separator + DateUtil.getDay(new Date()) + File.separator + "info.csv";
        return dir;
    }
}
