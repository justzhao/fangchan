package com.zhaopeng.fangchan.crawler.impl;

import com.zhaopeng.fangchan.CrawlerException;
import com.zhaopeng.fangchan.crawler.HourseCrawlerService;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by zhaopeng on 2017/9/9.
 */

public class HourseCrawlerServiceImpl implements HourseCrawlerService {


    private final String url;

    private final PageProcessor pageProcessor;

    private final Pipeline pipeline;

    private final int theads;


    private Spider spider;


    public HourseCrawlerServiceImpl(String url, PageProcessor pageProcessor, Pipeline pipeline, int theads) {
        this.url = url;
        this.pageProcessor = pageProcessor;
        this.pipeline = pipeline;
        this.theads = theads;
    }

    public String getUrl() {
        return url;
    }


    public void init() {

        spider = Spider.create(pageProcessor);
        spider.addPipeline(pipeline);
        spider.addUrl(url);
        spider.thread(theads);

    }

    public void start() throws CrawlerException {
        if (spider == null) {
            throw new CrawlerException("爬虫没有实例化");
        }
        spider.start();

    }


}
