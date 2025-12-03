package com.wangquocthai.java4.lab7.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity Employee - Nhân viên
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Employee {
    private String id;
    private String name;
    private Boolean gender;
    private Double salary;
}
