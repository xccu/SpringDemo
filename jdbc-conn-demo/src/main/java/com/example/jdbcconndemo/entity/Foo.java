package com.example.jdbcconndemo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//数据实体类
@Data
@Builder
public class Foo {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String bar;
}
