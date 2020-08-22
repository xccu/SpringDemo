package com.example.jasperdemo;

import com.example.jasperdemo.model.TestModel;
import com.example.jasperdemo.support.GenerateReportVo;
import com.example.jasperdemo.support.OutputType;
import com.example.jasperdemo.util.JasperReportUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class JasperDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(JasperDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		/*List<Map<String, Object>> dataset = new ArrayList<>();
		Map<String, Object> data;
		for (int i = 0; i < 10; i++) {
			data = new HashMap<>();
			data.put("name", "User" + i);
			data.put("age", i);
			dataset.add(data);
		}*/

		List<TestModel> dataset = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			TestModel model = new TestModel();
			model.setName("User" + i);
			model.setAge(i);
			dataset.add(model);
		}

		log.info(generate(dataset));
	}


	private String generate(List dataModels) {

		//设置报表参数
		Map<String, Object> params = new HashMap<>();
		params.put("unit-test", "abcd123");

		GenerateReportVo reportVo = new GenerateReportVo(); //报告输出实体
		reportVo.setOutputType(OutputType.PDF);				//输出报表文件类型
		reportVo.setReportName("testReport");				//输出报表文件名称
		reportVo.setTemplateId("TEST");	 					//模板名称
		reportVo.setReportDatas(dataModels);				//报表数据
		reportVo.setOutputPath("temp");						//报表输出路径
		reportVo.setParams(params);							//报表参数
		String path;
		path = JasperReportUtils.fillReport(reportVo);
		return path;
	}
}
