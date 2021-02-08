package com.example.demo.support;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

//定义接口

/**
 * 继承JpaRepository和JpaSpecificationExecutor接口
 * 同时具备了paRepository功能和Specification功能
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    Page<T> findByAuto(T example, Pageable pageable);

}
