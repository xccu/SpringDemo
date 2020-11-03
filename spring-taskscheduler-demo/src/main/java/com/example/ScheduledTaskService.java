package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

//计划任务执行类
@Slf4j
@Service  //@Service注解为Service类并注册到spring容器中
public class ScheduledTaskService {

	/**
	 * 	cron: 使用Cron语法来指定调度计划
	 * 	zone: 指定时区，默认为本地时区
	 * 	fixedDelay: 指定fixedDelay的值，它表示上一次任务执行完后多长时间启动下一次任务，单位默认是毫秒
	 * 	fixedRate: 指定上一次任务开始时间到下一次任务开始时间的间隔时间，单位默认是毫秒
	 * 	initialDelay: 指定提交调度任务后多长时间开始执行第一次任务
	 *
	 * 	https://blog.csdn.net/icarusliu/article/details/79531343
	 * 	https://www.cnblogs.com/chenpi/p/6222220.html
	 */

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	/**
	 *通过@Scheduled声明该方法是计划任务，使用fixedRate属性每隔固定时间（单位：毫秒）异步执行
	 */
	@Scheduled(fixedRate = 5000,initialDelay = 10000)
	public void reportCurrentTime() {
		log.info("fixedRate：每隔五秒执行一次 " + dateFormat.format(new Date()));
	}

	/**
	 *使用cron属性按照指定的时间执行，本例为每天11点28分执行，cron是Unix和Linux系统下的定时任务
	 */
	@Scheduled(cron = "0 28 11 ? * *"  )
	public void fixTimeExecution(){
		log.info("cron：在指定时间 " + dateFormat.format(new Date())+"执行");
	}

	/**
	 *使用fixedDelay属性每隔固定时间（单位：毫秒）异步执行
	 */
	@Scheduled(fixedDelay = 1000)
	public void fixedDelayExecution(){
		log.info("fixedDelay：每隔一秒执行一次 " + dateFormat.format(new Date())+"执行");
	}

	/*@Scheduled(initialDelay = 3000)
	public void initialDelayExecution(){
		log.info("initialDelay：每隔三秒执行一次 " + dateFormat.format(new Date())+"执行");
	}*/

}
