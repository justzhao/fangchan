package com.zhaopeng.fangchan.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 保存爬虫爬取的信息
 * Created by zhaopeng on 2017/9/9.
 */
public class LianjiaPipeline implements Pipeline {
    public void process(ResultItems resultItems, Task task) {

        Site site = task.getSite();

    }
}
