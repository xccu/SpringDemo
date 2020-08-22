package com.example.jdbcconndemo.entity;

import lombok.*;

@Data
@Builder
public class Voyage {
    //起点
    @Getter
    @Setter
    public String start;

    //终点
    @Getter
    @Setter
    public String end;

    //里程数
    @Getter
    @Setter
    public int mile;

}
