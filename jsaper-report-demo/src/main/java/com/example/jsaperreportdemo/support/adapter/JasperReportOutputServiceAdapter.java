package com.example.jsaperreportdemo.support.adapter;

import com.example.jsaperreportdemo.support.GenerateReportVo;
import com.example.jsaperreportdemo.util.JasperReportUtils;
import org.springframework.stereotype.Component;

/**
 * 报告输出适配器
 *
 */
@Component
public class JasperReportOutputServiceAdapter extends AbstractOutputServiceAdapter {

    @Override
    public String output(GenerateReportVo generateReportVo) {
        //填充报告
        return JasperReportUtils.fillReport(generateReportVo);
    }

}
