package com.example.jsaperreportdemo.support.annotation;

import com.example.jsaperreportdemo.support.OutputType;
import com.example.jsaperreportdemo.support.adapter.AbstractOutputServiceAdapter;
import com.example.jsaperreportdemo.support.adapter.JasperReportOutputServiceAdapter;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * ClassName: Report
 * Description: 报表注解，需注解在实现BaseReportService接口的实现类上，由@Component派生，可交由spring自动管理
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Inherited
public @interface Report {
    /**
     * 报告Service类型，必填
     *
     * @return String
     */
    @AliasFor(annotation = Component.class)
    String value();

    /**
     * 输出格式
     *
     * @return OutputType
     */
    OutputType outputType() default OutputType.TXT;

    /**
     * 模板ID 如用JasperReport，则为jasper模板名称，如果用某些模板引擎如freemarker，则为制作的其模板文件
     *
     * @return String
     */
    String templateId() default "";

    String[] subTemplateIds() default {};

    /**
     * 配置的输出工具适配器，默认为JasperReport(暂时还没引入), 可以通过 自定义的方式 引入其他输出工具
     *
     * @return
     */
    Class<? extends AbstractOutputServiceAdapter> outputService() default JasperReportOutputServiceAdapter.class;

}
