package com.example.jsaperreportdemo.support.context;

import com.example.jsaperreportdemo.support.OutputType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ReportContext
 * Description: 报表上下文
 *
 */
public class ReportContext implements Serializable {

/*    private final static String BEGIN_DATE = "beginDate";
    private final static String END_DATE = "endDate";*/

    /**
     * 当前处理的报表类型
     */
    private String currentReportType;

    /**
     * 构造数据或生成报表时需要外部传入的参数
     */
    @Getter
    private Map<String, Object> params = new HashMap<>();

    /**
     * 报表数据合并标识
     */
    @Getter
    @Setter
    private boolean currentMergeFlag = false;

    @Getter
    private String templateId = null;


    /**
     * 输出路径
     */
    @Getter
    @Setter
    private String outputPath;

    /**
     * 报告名称
     */
    @Getter
    @Setter
    private String fileName;

    //value = "报告中文名
    @Getter
    @Setter
    private String fileNameCN;

    /**
     * 报告生成日期
     */
    @Getter
    @Setter
    private LocalDateTime generateTime;

    /**
     * 各报表类型生成后对应的路径
     */
    @Getter
    private Map<String, ReportFileInfo> reportFileInfos = new HashMap<>();

    public void setCurrentReportType(String currentReportType) {
        this.currentReportType = currentReportType;
        final ReportFileInfo reportFileInfo = new ReportFileInfo();
        reportFileInfo.setReportCode(currentReportType);
        reportFileInfos.put(this.currentReportType, reportFileInfo);
    }

    /**
     * 添加参数
     *
     * @param key   参数
     * @param value 参数值
     */
    public void addParam(String key, Object value) {
        this.params.put(key, value);
    }

    /**
     * 添加错误
     *
     * @param error error
     */
    public void addError(Object error) {
        this.reportFileInfos.get(currentReportType).setStatus(false);
        this.reportFileInfos.get(currentReportType).getFileGenerateErrors().add(error);
    }


    // 设置路径
    public void setReportPath(String path) {
        this.reportFileInfos.get(currentReportType).setTime(LocalDateTime.now());
        this.reportFileInfos.get(currentReportType).setFilePath(path);
    }

    // 合并另一个报表数据
    public void mergeAnotherContext(ReportContext reportContext) {
        this.currentMergeFlag = true;
        this.getReportFileInfos().putAll(reportContext.reportFileInfos);
    }

    public void replaceTemplateId(String templateId) {
        this.templateId = templateId;
    }

    /**
     *
     */
    public void buildReportName(String reportName) {
        this.reportFileInfos.get(this.currentReportType).setFileName(reportName);
    }

    public String reportName() {
        return this.reportFileInfos.get(this.currentReportType).getFileName();
    }

    public void putFileSize(Long fileSize) {
        this.reportFileInfos.get(this.currentReportType).setFileSize(fileSize);
    }

    public void putFileIsNull() {
        this.reportFileInfos.get(this.currentReportType).setIsnull(true);
    }

    public void putFileType(OutputType outputType) {
        this.reportFileInfos.get(this.currentReportType).setFileType(outputType);
    }

}