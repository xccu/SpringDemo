package com.example.demo;

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
	 * 每隔1秒执行一次，上一次开始执行时间点之后5秒再执行
	 */
	@Scheduled(fixedRate = 1000,zone = "Asia/Shanghai")
	public void fixedRateRun() {
		log.info("fixedRate：\t\t每隔一秒执行一次 " + dateFormat.format(new Date()));
	}

	/**
	 * 与fixedRate相同，只是使用字符串的形式。并支持占位符。如：
	 * @Scheduled(fixedDelayString = "1000")
	 * 占位符的使用(配置文件中有配置：time.fixedRate=1000)：
	 */
	@Scheduled(fixedRateString = "1000")
	public void fixedRateStringRun() {
		log.info("fixedRateStr：\t每隔一秒执行一次 " + dateFormat.format(new Date()));
	}

	/**
	 *使用cron属性按照指定的时间执行，cron是Unix和Linux系统下的定时任务 格式[秒] [分] [小时] [日] [月] [周] [年]
	 * 每天11点28分执行
	 */
	@Scheduled(cron = "0 21 16 * * *"  )
	public void cornTimeRun() {
		log.info("cron-Time：\t\t在指定时间执行 " + dateFormat.format(new Date()));
	}

	/**
	 *使用cron属性按照指定的时间执行，cron是Unix和Linux系统下的定时任务 格式[秒] [分] [小时] [日] [月] [周] [年]
	 * 每隔5秒执行一次，与fixedRate不同，任务开启后第一次不会执行，会5秒后执行一次
	 */
	@Scheduled(cron = "*/5 * * * * *"  )
	public void cornSpanRun() {
		log.info("cron-Span：\t\t每隔五秒执行一次 " + dateFormat.format(new Date()));
	}

	/**
	 *使用fixedDelay属性每隔固定时间（单位：毫秒）异步执行
	 * 每隔5秒执行一次,上一次执行完毕时间点之后多长时间再执行
	 */
	@Scheduled(fixedDelay = 5000)
	public void fixedDelayRun() {
		log.info("fixedDelay：\t每隔五秒执行一次 " + dateFormat.format(new Date()));
	}

	/**
	 * 与fixedDelay相同，只是使用字符串的形式。并支持占位符。如：
	 * @Scheduled(fixedDelayString = "5000")
	 * 占位符的使用(配置文件中有配置：time.fixedDelay=5000)：
	 */
	@Scheduled(fixedDelayString = "${time.fixedDelay}")
	void FixedDelayStringRun() {
		log.info("FixedDelayStr：\t读取配置文件执行 " + dateFormat.format(new Date()));
	}

	/**
	 * 使用initialDelay属性第一次延迟多长时间后再执行
	 * 第一次延迟10秒后执行，之后按fixedRate的规则每5秒执行一次
	 */
	@Scheduled(initialDelay=10000, fixedRate=5000)
	void initialDelayRun() {
		log.info("initialDelay：\t延迟10秒执行 " + dateFormat.format(new Date()));
	}


	/**
	 * 与initialDelay相同，只是使用字符串的形式。并支持占位符。如：
	 * @Scheduled(fixedDelayString = "10000",fixedRate=5000)
	 * 占位符的使用(配置文件中有配置：time.initialDelay=10000)：
	 */
	@Scheduled(initialDelayString="${time.initialDelay}", fixedRate=5000)
	void initialDelayStringRun() {
		log.info("initialDelayStr：\t延迟10秒执行 " + dateFormat.format(new Date()));
	}
}
