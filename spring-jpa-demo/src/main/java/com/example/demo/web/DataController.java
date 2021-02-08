package com.example.demo.web;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {
	//1 Spring Data JPA已自动为你注册bean，所以可自动注入
	@Autowired
	UserRepository userRepository;

	/**
	 * 查询全部
	 * localhost:8080
	 * @return
	 */
	@RequestMapping("/")
	public List<User> all(){
		List<User> users = userRepository.findAll();
		return users;
	}

	/**
	 * 保存
	 * save支持批量保存：<S extends T> Iterable<S> save(Iterable<S> entities);
	 * localhost:8080/save?name=user1&age=3
	 *
	 * 删除：
	 * 删除支持使用id，对象以，批量删除及删除全部：
	 * void delete(ID id);
	 * void delete(T entity);
	 * void delete(Iterable<? extends T> entities);
	 * void deleteAll();
	 * 
	 */
	@RequestMapping("/save")
	public User save(String name, int age){
		User user = User.builder()
				.name(name)
				.age(age).build();
		User u = userRepository.save(user);
		return u;
	}

	/**
	 * localhost:8080/delete?id=1
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public List<User> delete(Integer id){
		userRepository.deleteById(id);
		return userRepository.findAll();
	}
	
	/**
	 * 测试findByName
	 * localhost:8080/findByName?name=Weslie
	 */
	@RequestMapping("/findByName")
	public List<User> findByName(String name){
		List<User> people = userRepository.findByName(name);
		return people;
	}
	
	/**
	 * 测试findByNameAndAge
	 * localhost:8080/findByNameAndAge?name=Weslie&age=12
	 */
	@RequestMapping("/findByNameAndAge")
	public User findByNameAndAge(String name, Integer age){
		User people = userRepository.findByNameAndAge(name, age);
		return people;
	}
	
	/**
	 * 测试withNameAndAgeQuery
	 * localhost:8080/withNameAndAgeQuery?name=Weslie&age=12
	 */
	@RequestMapping("/withNameAndAgeQuery")
	public User withNameAndAgeQuery(String name, Integer age){
		User p = userRepository.withNameAndAgeQuery(name, age);
		return p;
	}
	
	/**
	 * 测试withNameAndAddressNamedQuery
	 * localhost:8080/withNameAndAgeNamedQuery?name=Weslie&age=12
	 */
	@RequestMapping("/withNameAndAgeNamedQuery")
	public User withNameAndAgeNamedQuery(String name, Integer age){
		User p = userRepository.withNameAndAgeNamedQuery(name, age);
		return p;
	}
	
	/**
	 * 测试排序
	 * localhost:8080/sort
	 */
	@RequestMapping("/sort")
	public List<User> sort(){
		List<User> people = userRepository.findAll(Sort.by(Direction.ASC,"age"));
		return people;
	}
	
	/**
	 * 测试分页
	 * localhost:8080/page
	 */
	@RequestMapping("/page")
	public Page<User> page(){
		
		Page<User> pagePeople = userRepository.findAll(PageRequest.of(1, 2));
		return pagePeople;
	}

	/**
	 * 无构造条件查询：http://localhost:8080/auto
	 * 查询名字中包含W的记录：http://localhost:8080/auto?name=W
	 * @param user
	 * @return
	 */
	@RequestMapping("/auto")
	public Page<User> auto(User user){
		Page<User> pageUser = userRepository.findByAuto(user, PageRequest.of(0, 10));
		return pageUser;
	}

}
