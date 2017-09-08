package com.zhaopeng.fangchan.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 爬虫的核心业务逻辑
 * Created by zhaopeng on 2017/9/9.
 */
public class LianjiaProcessor implements PageProcessor {

    private final Site site = Site.me().setRetryTimes(3).setSleepTime(100);



    public void process(Page page) {

    }

    public Site getSite() {
        return site;
    }
}
