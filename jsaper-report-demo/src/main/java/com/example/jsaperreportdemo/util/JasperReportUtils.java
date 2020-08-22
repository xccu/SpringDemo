package com.example.jsaperreportdemo.util;

import com.example.jsaperreportdemo.configuation.ApplicationContextHolder;
import com.example.jsaperreportdemo.support.GenerateReportVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;

import java.io.*;

@Slf4j
public class JasperReportUtils {

    private JasperReportUtils() { }


    private static Environment getEnv() {
        return ApplicationContextHolder.getApplicationContext().getBean(Environment.class);
    }

    /**
     * 获取配置文件中的节点（document.root）
     * @return
     */
    public static String getRootPath() {
        return getEnv().getProperty("document.root");
    }

    /**
     * 获取配置文件中的节点（report.template.pat）
     * @return
     */
    public static String getTemplatePath() {
        return getEnv().getProperty("report.template.path");
    }

    /**
     * 生成报表文件，返回路径
     *
     * @param vo
     * @return
     */
    public static String fillReport(GenerateReportVo vo) {
        try {

            String outputFilePath = checkParentPath(getRootPath() + "/" + vo.getOutputPath()) + "/" + vo.getReportName() + vo.getOutputType().getSuffix();
            JasperPrint print = fillPrint(vo);
            exportReport(print, outputFilePath, vo);
            return outputFilePath;
        } catch (Exception e) {
            log.error("报表生成错误", e);
            throw new RuntimeException("报表生成错误", e);
        }
    }

    private static String checkParentPath(String destPath) throws IOException {
        final File file = new File(destPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return destPath;
    }

    /**
     * 获取模板
     *
     * @param vo
     * @return
     */
    private static JasperPrint fillPrint(GenerateReportVo vo) {
        JasperPrint print = null;
        try {
            //存放模板的地址
            String jasperpath = getTemplatePath() + "/" + vo.getTemplateId() + ".jasper";
            File jasperFile = ResourceUtils.getFile(jasperpath);
            //生成JasperPrint
            print = JasperFillManager.fillReport(
                    jasperFile.getPath(),
                    vo.getParams(),
                    new JRBeanCollectionDataSource(vo.getReportDatas()));
        } catch (FileNotFoundException e) {
            log.error("Jasper模板文件不存在", e);
            throw new RuntimeException("Jasper模板文件不存在");
        } catch (JRException e) {
            log.error("Jasper输出报告失败", e);
            throw new RuntimeException("Jasper输出报告失败");
        } catch (Exception e) {
            throw new RuntimeException("系统异常");
        }
        return print;
    }


    /**
     * 输出报告
     *
     * @param print
     * @param outputFile
     * @param vo
     * @throws JRException
     * @throws IOException
     */
    private static void exportReport(JasperPrint print, String outputFile, GenerateReportVo vo)
            throws JRException, IOException {

        try (OutputStream outputStream = new FileOutputStream(outputFile);) {
            //输出不同类型的文档
            switch (vo.getOutputType()) {
                case PDF:   pdf(print, outputStream, vo);   break;
                case XLS:   xls(print, outputStream, vo);   break;
                case CSV:   csv(print, outputStream);       break;
                case XLSX:  xlsx(print, outputStream, vo);  break;
                case TXT:   txt(print, outputStream, vo);   break;
                default: break;
            }
        }

    }

    private static void xls(JasperPrint jasperPrint, OutputStream outputStream, GenerateReportVo vo)
            throws JRException {
        JRXlsExporter exporter = new JRXlsExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        SimpleXlsReportConfiguration configuration = (SimpleXlsReportConfiguration) vo
                .getReportExportConfiguration();
        if (configuration == null) {
            configuration = new SimpleXlsReportConfiguration();
        }

        configuration.setOnePagePerSheet(false);
        exporter.setConfiguration(configuration);

        exporter.exportReport();
    }

    private static void csv(JasperPrint jasperPrint, OutputStream outputStream)
            throws JRException {
        JRCsvExporter exporter = new JRCsvExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));

        exporter.exportReport();
    }

    private static void pdf(JasperPrint jasperPrint, OutputStream outputStream, GenerateReportVo vo)
            throws JRException {
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        SimplePdfExporterConfiguration configuration = (SimplePdfExporterConfiguration) vo
                .getReportExportConfiguration();
        if (configuration == null) {
            configuration = new SimplePdfExporterConfiguration();
        }
        configuration.setCreatingBatchModeBookmarks(true);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }

    private static void xlsx(JasperPrint jasperPrint, OutputStream outputStream,
                             GenerateReportVo vo)
            throws JRException {
        JRXlsxExporter exporter = new JRXlsxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        SimpleXlsxReportConfiguration configuration = (SimpleXlsxReportConfiguration) vo
                .getReportExportConfiguration();
        if (configuration == null) {
            configuration = new SimpleXlsxReportConfiguration();
        }

        configuration.setOnePagePerSheet(false);
        exporter.setConfiguration(configuration);

        exporter.exportReport();
    }

    private static void txt(JasperPrint jasperPrint, OutputStream outputStream, GenerateReportVo vo)
            throws JRException {
        JRTextExporter exporter = new JRTextExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
        SimpleTextReportConfiguration configuration = (SimpleTextReportConfiguration) vo
                .getReportExportConfiguration();
        if (configuration == null) {
            configuration = new SimpleTextReportConfiguration();
        }
        configuration.setCharWidth(7.238f);
        configuration.setCharHeight(13.948f);
        exporter.setConfiguration(configuration);

        exporter.exportReport();
    }

}
