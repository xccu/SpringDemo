package com.example.mybatispagehelperdemo;

import com.example.mybatispagehelperdemo.dao.UserDao;
import com.example.mybatispagehelperdemo.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@SpringBootApplication
@MapperScan("com.example.mybatispagehelperdemo.dao") //指定扫描包,使用后Dao层可不使用Maper注解
public class MybatisPagehelperDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(MybatisPagehelperDemoApplication.class, args);
	}

	@Autowired
	UserDao userDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		onRowBounds();
		onPageHelperParams();
		onPageHelper();
	}

	/**
	 * 使用RowBounds
	 */
	private void onRowBounds() {
		log.info("=========onRowBounds==========");
		//使用RowBounds分页
		//第1页，每页3条记录
		userDao.findAllByRowBounds(new RowBounds(1, 3))
				.forEach(c -> log.info("Page(1) User {}", c));
		//第2页，每页3条记录
		userDao.findAllByRowBounds(new RowBounds(2, 3))
				.forEach(c -> log.info("Page(2) User {}", c));
		log.info("===================");

		//页面大小为0，取出所有记录
		userDao.findAllByRowBounds(new RowBounds(1, 0))
				.forEach(c -> log.info("Page(1) User {}", c));
		log.info("===================");
	}

	/**
	 * 使用带参数的PageHelper
	 */
	private void onPageHelperParams(){
		log.info("=========onPageHelperParams==========");
		//使用参数传入页码和页大小
		userDao.findAllByParam(1, 3)
				.forEach(c -> log.info("Page(1) User {}", c));
		log.info("===================");

		List<User> list = userDao.findAllByParam(2, 3);
		//打印页面信息
		PageInfo page = new PageInfo(list);
		log.info("PageInfo: {}", page);
	}

	/**
	 * 使用不带参数的PageHelper
	 */
	private void onPageHelper(){
		log.info("=========onPageHelper==========");

		List<User> users;
		int page =1;

		//是否还有下一页
		boolean isHasNextPage = true;
		while(isHasNextPage) {

			//使用PageHelper插件设置页码和页大小
			PageHelper.startPage(page, 3);
			PageInfo<User> pageInfo = new PageInfo<>(userDao.findAll());

			//users会被累加
			//users = Stream.of(pageInfo.getList(), users).flatMap(Collection::stream).collect(Collectors.toList());

			users = Stream.of(pageInfo.getList()).flatMap(Collection::stream).collect(Collectors.toList());
			isHasNextPage = pageInfo.isHasNextPage();
			for(User user : users){
				log.info("Page({}) User {}",page, user);
			}
			page++;
		}
	}
}
