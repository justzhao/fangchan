package com.zhaopeng.fangchan.crawler;

import com.zhaopeng.fangchan.CrawlerException;

/**
 * Created by zhaopeng on 2017/9/9.
 */
public interface HourseCrawlerService {


    /**
     * 初始化爬虫信息
     */
    public void  init();


    /**
     * 启动爬虫
     */
    public void  start() throws CrawlerException;



}
