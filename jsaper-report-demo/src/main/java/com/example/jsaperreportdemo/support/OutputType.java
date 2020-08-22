package com.example.jsaperreportdemo.support;

import lombok.Getter;

/**
 * 输出类型枚举
 */
public enum OutputType {

    XLSX(".xlsx"), XLS(".xls"), TXT(".txt"), PDF(".pdf"), CSV(".csv");

    @Getter
    private String suffix;

    OutputType(String suffix) {
        this.suffix = suffix;
    }
}