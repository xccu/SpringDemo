package com.example.jasperdemo.support;

import lombok.Data;
import net.sf.jasperreports.export.ReportExportConfiguration;

import java.util.List;
import java.util.Map;

@Data
public class GenerateReportVo {

    /**
     * 模板名称
     */
    private String templateId;

    /**
     * 输出类型
     */
    private OutputType outputType;

    /**
     * 报表数据
     */
    private List reportDatas;

    /**
     * 外部参数
     */
    private Map<String, Object> params;

    /**
     * 报表名称
     */
    private String reportName;

    /**
     * 输出路径
     */
    private String outputPath;

    /**
     * 生成报表的参数.
     */
    private ReportExportConfiguration reportExportConfiguration;
}
