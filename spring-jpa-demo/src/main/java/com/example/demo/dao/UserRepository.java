package com.example.demo.dao;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 1 使用方法名查询，接收一个name参数，返回值为列表
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 2 使用方法名查询，接收name和age，返回值为单个对象
     * @param name
     * @param age
     * @return
     */
    User findByNameAndAge(String name, int age);

    /**
     * 3 使用Query查询，参数按照名称绑定
     * @param name
     * @param age
     * @return
     */
    @Query("select u from User u where u.name= :name and u.age= :age")
    User withNameAndAgeQuery(@Param("name") String name, @Param("age") int age);

    /**
     * 4 使用NamedQuery查询，请注意在实体类中做的 @NamedQuery定义
     * @param name
     * @param age
     * @return
     */
    User withNameAndAgeNamedQuery(String name, int age);
}
