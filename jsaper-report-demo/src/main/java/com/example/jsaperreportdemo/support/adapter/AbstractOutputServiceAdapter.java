package com.example.jsaperreportdemo.support.adapter;

import com.example.jsaperreportdemo.support.GenerateReportVo;

/**
 * ClassName: AbstractOutputServiceAdapter
 * Description: 输出工具适配器，拓展输出工具
 */
public abstract class AbstractOutputServiceAdapter {

    public final static Class<JasperReportOutputServiceAdapter> DEFAULT = JasperReportOutputServiceAdapter.class;

    /**
     * 输出
     *
     * @param generateReportVo 生成报表需要的数据封装
     * @return String 生成的文件路径
     */
    public abstract String output(GenerateReportVo generateReportVo);

}
