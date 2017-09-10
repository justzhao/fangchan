package com.zhaopeng.fangchan.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by zhaopeng on 2017/9/9.
 */
public class LianjiaProcessor implements PageProcessor {


    private final Processor processor;


    private String agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";


    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setUserAgent(agent);

    public LianjiaProcessor(Processor processor) {

        this.processor = processor;
    }


    public void process(Page page) {

        processor.processPage(page);


    }




    public Site getSite() {
        return site;
    }



}
