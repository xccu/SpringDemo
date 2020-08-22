package com.example.jsaperreportdemo.support;

import com.example.jsaperreportdemo.support.adapter.AbstractOutputServiceAdapter;
import com.example.jsaperreportdemo.support.annotation.Report;
import com.example.jsaperreportdemo.support.context.ReportContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: Reporter
 * Description: 报表工具类，提供生成报表入口
 */
@Slf4j
public class Reporter {


    private static final Map<String, BaseReportService> REPORTS = new HashMap<>();
    private static final Map<Class<? extends AbstractOutputServiceAdapter>, AbstractOutputServiceAdapter> OUTPUTSERVICES = new ConcurrentHashMap<>();

    protected static void configReports(Map<String, Object> reports) {
        if (reports != null && !reports.isEmpty()) {
            reports.entrySet().forEach(entry -> REPORTS.put(entry.getKey(), (BaseReportService) entry.getValue()));
        }
    }

    protected static void configAdapters(AbstractOutputServiceAdapter adapter) {
        OUTPUTSERVICES.put(adapter.getClass(), adapter);
    }

    private Reporter() {
    }

    /**
     * 根据reportTypes生成报表。
     *
     * @param outputPath  输出路径
     * @param context     报表上下文
     * @param reportTypes 报表类型（对应的jasper文件名）
     * @param <C>         上下文泛型
     * @return ReportContext
     */
    public static <C extends ReportContext> C generateReport(String outputPath, C context, String... reportTypes) {
        if (reportTypes == null || reportTypes.length == 0) {
            return context;
        }
        List dataModels;
        String reportName;
        //设置输出路径
        context.setOutputPath(outputPath);
        //遍历报表类型生成报表
        for (int i = 0; i < reportTypes.length; i++) {
            String reportType = reportTypes[i];
            //报表类型（jasper文件名）
            context.setCurrentReportType(reportType);
            context.replaceTemplateId(null);
            //获取报表类型对应Service
            final BaseReportService reportService = REPORTS.get(reportType);
            if (reportService == null) {
                context.addError(String.format("ReportType[%s] not exists!", reportType));
                continue;
            }
            try {
                //生成报告文件名
                reportName = reportService.generateReportName(context);
                context.buildReportName(reportName);
                //生成报告数据
                dataModels = reportService.generateReportData(context);
                if (context.isCurrentMergeFlag()) {
                    context.setCurrentMergeFlag(false);
                    continue;
                }
                //生成报告输出路径
                context.setReportPath(generate(reportService, context, dataModels));
            } catch (Exception e) {
                context.addError(e.getMessage());
                log.error(e.getMessage(), e);
            }
        }
        return context;
    }

    /**
     * 生成所有已注册的报表
     *
     * @param outputPath 输出路径
     * @param context    上下文
     * @param <C>        上下文泛型
     * @return ReportContext
     */
    public static <C extends ReportContext> C generateAllReports(String outputPath, C context) {
        List dataModels;
        String reportName;
        context.setOutputPath(outputPath);
        for (Map.Entry<String, BaseReportService> entry : REPORTS.entrySet()) {
            context.setCurrentReportType(entry.getKey());
            context.replaceTemplateId(null);
            try {
                reportName = entry.getValue().generateReportName(context);
                context.buildReportName(reportName);
                dataModels = entry.getValue().generateReportData(context);
                if (context.isCurrentMergeFlag()) {
                    context.setCurrentMergeFlag(false);
                    continue;
                }
                context.setReportPath(generate(entry.getValue(), context, dataModels));
            } catch (Exception e) {
                context.addError(e.getMessage());
                log.error(e.getMessage(), e);
            }
        }

        return context;
    }

    /**
     * 生成报告
     * @param service
     * @param context
     * @param dataModels
     * @return 报告输出路径
     */
    private static String generate(BaseReportService service, ReportContext context, List dataModels) {
        //通过注解的方式进行适配
        final Report report = service.getClass().getDeclaredAnnotation(Report.class);
        //输出报告参数类
        GenerateReportVo reportVo = new GenerateReportVo();
        //输出报告类型
        reportVo.setOutputType(report.outputType());
        context.putFileType(report.outputType());
        //输出报告名称
        reportVo.setReportName(context.reportName());
        //输出报告对应japsper模板
        if (StringUtils.isNotBlank(context.getTemplateId())) {
            reportVo.setTemplateId(context.getTemplateId());
        } else if (StringUtils.isNotBlank(report.templateId())) {
            reportVo.setTemplateId(report.templateId());
        }
        dataModels = dataModels == null ? new ArrayList() : dataModels;
        if (dataModels.isEmpty()) {
            context.putFileIsNull();
        }

        //报告数据模型
        reportVo.setReportDatas(dataModels);
        //报告输出路径
        reportVo.setOutputPath(context.getOutputPath());
        //报告参数
        reportVo.setParams(context.getParams());
        String path;
        //检索对应的Service
        if (OUTPUTSERVICES.containsKey(report.outputService())) {
            //生成报告并输出完整路径
            //通过输出工具适配器输出
            path = OUTPUTSERVICES.get(report.outputService()).output(reportVo);
            File file = new File(path);
            //报告文件大小
            context.putFileSize(file.length());
        } else {
            path = "";
            context.addError(report.outputService().getSimpleName() + " not exist！");
        }
        return path;
    }

}
