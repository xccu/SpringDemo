package com.example.springtransactionaldemo.model;

import lombok.Data;

@Data
public class Score {
    private Integer grade;
    private String course;

    public Score(Integer grade,String course){
        this.grade=grade;
        this.course=course;
    }
}
