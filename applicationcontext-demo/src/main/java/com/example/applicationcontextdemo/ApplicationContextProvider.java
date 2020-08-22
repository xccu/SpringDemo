package com.example.applicationcontextdemo;

import com.example.applicationcontextdemo.annotation.IoService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;
/**
 * 获取Spring上下文对象
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware
{
    /**
     * 上下文对象实例
     */
    private ApplicationContext applicationContext;

    /**
     * 加载Spring配置文件时，
     * 如果Spring配置文件中所定义或者注解自动注入的Bean类实现了ApplicationContextAware 接口
     * 那么在加载Spring配置文件时，会自动调用ApplicationContextAware 接口中的
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     * @return
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     * @param name
     * @return
     */
    public Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通Annotation注解获取Beans.
     * @param clazz
     * @return
     */
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> clazz){
        return getApplicationContext().getBeansWithAnnotation(clazz);
    }

    /**
     * 通过class获取Beans.
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Map<String, T>  getBeansOfType(Class<T> clazz){
        return getApplicationContext().getBeansOfType(clazz);
    }

    /**
     * 通过name,以及Class返回指定的Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}