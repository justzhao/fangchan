package com.zhaopeng.fangchan;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.CountDownLatch;

@EnableAutoConfiguration(exclude = {DataSourceTransactionManagerAutoConfiguration.class,
        SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.zhaopeng.fangchan"})
public class App {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(App.class).web(false).addCommandLineProperties(true).run();
        //阻塞主线程
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
