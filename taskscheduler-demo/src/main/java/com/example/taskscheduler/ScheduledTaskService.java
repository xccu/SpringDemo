package com.example.taskscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduledTaskService {

    //1.通过@Scheduled声明该方法是计划任务，使用fixedRate属性每隔固定时间执行（单位：毫秒）
    @Scheduled(fixedRate = 1000)
    public void task1(){
        System.out.println("每隔一秒执行一次");
    }

    //2.使用cron属性可按照指定的时间执行，本例是每天14点47分执行
    @Scheduled(cron = "0 57 14 * * ?")//cron表达式
    public void task2(){
        System.out.println("指定时间执行:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}