package com.example.jsaperreportdemo.service;


import com.example.jsaperreportdemo.model.UserModel;
import com.example.jsaperreportdemo.support.BaseReportService;
import com.example.jsaperreportdemo.support.OutputType;
import com.example.jsaperreportdemo.support.annotation.Report;
import com.example.jsaperreportdemo.support.context.ReportContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Report(value = "TEST",
        templateId = "TEST",
        outputType = OutputType.PDF)
public class UserReportService implements BaseReportService<UserModel> {
    @Override
    public String generateReportName(ReportContext context) {
        return context.getFileName();
    }

    @Override
    public List<UserModel> generateReportData(ReportContext context) {

        context.addParam("unit-test", "abcd123");
        List<UserModel> dataset = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserModel model = new UserModel();
            model.setName("User" + i);
            model.setAge(i);
            dataset.add(model);
        }

        return dataset;
    }
}
