package com.zhaopeng.fangchan.crawler.impl;

import com.zhaopeng.fangchan.CrawlerException;
import com.zhaopeng.fangchan.crawler.HourseCrawlerService;
import com.zhaopeng.fangchan.entity.City;
import com.zhaopeng.fangchan.entity.CrawlerConstant;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by zhaopeng on 2017/9/9.
 */

public class HourseCrawlerServiceImpl implements HourseCrawlerService {


    private final City city;

    private final PageProcessor pageProcessor;

    private final Pipeline pipeline;

    private final int threads;


    private Spider spider;


    public HourseCrawlerServiceImpl(City city, PageProcessor pageProcessor, Pipeline pipeline, int threads) {
        this.city = city;
        this.pageProcessor = pageProcessor;
        this.pipeline = pipeline;
        this.threads = threads;
    }


    public void init() {

        spider = Spider.create(pageProcessor);
        spider.addPipeline(pipeline);
        spider.addUrl(city.getUrl());
        spider.thread(threads);
        Site site = spider.getSite();
        site.addCookie(CrawlerConstant.CITY, city.getName());
    }


    public void start() throws CrawlerException {
        if (spider == null) {
            throw new CrawlerException("爬虫没有实例化");
        }
        spider.start();

    }


}
