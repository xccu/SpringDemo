package com.example.jsaperreportdemo.support;

import com.example.jsaperreportdemo.support.adapter.AbstractOutputServiceAdapter;
import com.example.jsaperreportdemo.support.annotation.Report;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

//继承接口ApplicationContextAware
@Component
public class ReportBeanFactoryPostProcessor implements ApplicationContextAware {

    /**
     * 加载Spring配置文件时，如果Spring配置文件中所定义或者注解自动注入的Bean类实现了ApplicationContextAware 接口
     * 那么在加载Spring配置文件时，会自动调用ApplicationContextAware 接口中的
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //getBeansWithAnnotation方法通过注解获取bean
        final Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Report.class);
        Reporter.configReports(beans);
        //getBeansOfType方法通过类型获取AbstractOutputServiceAdapter
        Map<String, AbstractOutputServiceAdapter> adapterBeans = applicationContext.getBeansOfType(AbstractOutputServiceAdapter.class);
        //遍历配置适配器类
        adapterBeans.values().forEach(bean -> Reporter.configAdapters(bean));
    }
}
