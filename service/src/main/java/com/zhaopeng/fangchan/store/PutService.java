package com.zhaopeng.fangchan.store;


import com.google.common.collect.Lists;
import com.zhaopeng.fangchan.entity.EstateInfo;
import com.zhaopeng.fangchan.util.CSVUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by zhaopeng on 2017/9/14.
 */
public class PutService extends ServiceThread {

    private static final Logger logger = LoggerFactory.getLogger(PutService.class);

    private volatile List<EstateInfo> requestsWrite = Lists.newArrayList();

    private volatile List<EstateInfo> requestsRead = Lists.newArrayList();


    public String getServiceName() {
        return "putService";
    }

    public void putRequest(final EstateInfo request) {
        synchronized (this) {
            this.requestsWrite.add(request);
            if (!this.hasNotified) {
                this.hasNotified = true;
                this.notify();
            }
        }
    }


    public void run() {

        logger.info(this.getServiceName() + " service started");

        while (!this.isStoped()) {
            try {
                this.waitForRunning(0);
                this.doCommit();
            } catch (Exception e) {
                logger.error(this.getServiceName() + " service has exception. ", e);
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            logger.error("GroupCommitService Exception, ", e);
        }
        synchronized (this) {
            this.swapRequests();
        }
        this.doCommit();
        logger.info(this.getServiceName() + " service end");
    }

    private void swapRequests() {
        List<EstateInfo> tmp = this.requestsWrite;
        this.requestsWrite = this.requestsRead;
        this.requestsRead = tmp;
    }

    private void doCommit() {
        if (!this.requestsRead.isEmpty()) {

            for (EstateInfo req : this.requestsRead) {

               CSVUtil.appendContent(req.getDir(),req.getContent());
            }
            this.requestsRead.clear();

        }
    }
}
