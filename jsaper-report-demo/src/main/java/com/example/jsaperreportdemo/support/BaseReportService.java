package com.example.jsaperreportdemo.support;

import com.example.jsaperreportdemo.support.context.ReportContext;

import java.util.List;

/**
 * ClassName: BaseReportService
 * Description: 报表数据构造接口
 *
 */
public interface BaseReportService<T> {

    /**
     * 构造报表文件名
     *
     * @param context 上下文
     */
    String generateReportName(ReportContext context);

    /**
     * 构造数据报表
     *
     * @param context 上下文
     * @return List<T> 报表数据
     */
    List<T> generateReportData(ReportContext context);

}
