package com.zhaopeng.fangchan.crawler.impl;

import com.zhaopeng.fangchan.crawler.HourseCrawlerService;
import com.zhaopeng.fangchan.entity.City;
import com.zhaopeng.fangchan.entity.CrawlerConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by zhaopeng on 2017/9/9.
 */
@Service
public class HourseCrawlerServiceImpl implements HourseCrawlerService,InitializingBean {


    private static final Logger logger = LoggerFactory.getLogger(HourseCrawlerServiceImpl.class);


    @Autowired
    private City city;

    @Autowired
    private  PageProcessor pageProcessor;

    @Autowired
    private  Pipeline pipeline;

    @Value("${crawler.threads}")
    private  int threads;


    private Spider spider;




    public void init() {

        spider = Spider.create(pageProcessor);
        spider.addPipeline(pipeline);
        spider.addUrl(city.getUrl());
        spider.thread(threads);
        Site site = spider.getSite();

        site.addCookie("lianjia_uuid","de3b498c-a3b3-472c-a7eb-581b48a94123");
        site.addCookie(CrawlerConstant.CITY, city.getName());
        site.addCookie(CrawlerConstant.TYPE,city.getType());
        site.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        site.addHeader("Accept-Encoding","gzip, deflate, br");
        site.addHeader("Accept-Language","zh-CN,zh;q=0.8");
        site.addHeader(" Cache-Control","max-age=0");

        start();
    }


    public void start()  {
        if (spider == null) {
            logger.error("爬虫没有实例化");
        }
        spider.start();

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
