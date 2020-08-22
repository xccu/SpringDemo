package com.example.jsaperreportdemo.support.context;

import com.example.jsaperreportdemo.support.OutputType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * com.airtransport.report.report.support.context.ReportFileInfo
 *
 * @author Wang Yang, 2020-07-07
 * @version OPRA v.1.0.0
 */
@Data
public class ReportFileInfo implements Serializable {

    /**
     * 文件生成成功与否 true|false
     */
    private boolean status = true;
    /**
     * 文件生成是否为空
     */
    private boolean isnull = false;
    /**
     * 文件生成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime time;

    /**
     * 报告代码
     */
    private String reportCode;

    /**
     * 报告文件名
     */
    private String fileName;

    /**
     * 报告文件路径
     */
    private String filePath;

    /**
     * 报告文件大小 单位MB
     */
    private Long fileSize;

    /**
     * 报告文件格式
     */
    private OutputType fileType;

    /**
     * 报告文件生成时错误
     */
    private List<Object> fileGenerateErrors = new ArrayList<>();
}
