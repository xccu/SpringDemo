/*
 * Copyright (c) Travelsky Corp.
 * All Rights Reserved.
 */
package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private Integer gender;
    private Integer deptId;
}
