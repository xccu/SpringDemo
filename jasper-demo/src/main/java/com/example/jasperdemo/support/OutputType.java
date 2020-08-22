package com.example.jasperdemo.support;

import lombok.Getter;

public enum OutputType {

    XLSX(".xlsx"), XLS(".xls"), TXT(".txt"), PDF(".pdf"), CSV(".csv");

    @Getter
    private String suffix;

    OutputType(String suffix) {
        this.suffix = suffix;
    }
}