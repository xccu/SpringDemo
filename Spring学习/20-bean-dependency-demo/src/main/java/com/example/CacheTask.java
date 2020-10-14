package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class CacheTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("刷新缓存："
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
