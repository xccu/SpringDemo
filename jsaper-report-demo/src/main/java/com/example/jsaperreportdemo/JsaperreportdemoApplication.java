package com.example.jsaperreportdemo;

import com.example.jsaperreportdemo.support.Reporter;
import com.example.jsaperreportdemo.support.context.ReportContext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class JsaperreportdemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(JsaperreportdemoApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {


        ReportContext context = new ReportContext();
        context.setFileName("UserReport");
        context.setFileNameCN("用户报告");

        context = Reporter.generateReport("temp", context, "TEST");
        String path = context.getReportFileInfos().get("TEST").getFilePath();
        log.info(path);
    }


/*    private String generate() {

        List<TestModel> dataset = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestModel model = new TestModel();
            model.setName("User" + i);
            model.setAge(i);
            dataset.add(model);
        }



        //设置报表参数
        Map<String, Object> params = new HashMap<>();
        params.put("unit-test", "abcd123");

        GenerateReportVo reportVo = new GenerateReportVo(); //报告输出实体
        reportVo.setOutputType(OutputType.PDF);				//输出报表文件类型
        reportVo.setReportName("testReport");				//输出报表文件名称
        reportVo.setTemplateId("TEST");	 					//模板名称
        reportVo.setReportDatas(dataset);				//报表数据
        reportVo.setOutputPath("temp");						//报表输出路径
        reportVo.setParams(params);							//报表参数
        String path;
        path = JasperReportUtils.fillReport(reportVo);
        return path;
    }*/

}
