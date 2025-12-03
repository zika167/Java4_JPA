package com.wangquocthai.java4.lab7.entity;

/**
 * Entity Employee - Nhân viên
 */
public class Employee {
    private String id;
    private String name;
    private Boolean gender;
    private Double salary;
    
    public Employee() {
    }
    
    public Employee(String id, String name, Boolean gender, Double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean getGender() {
        return gender;
    }
    
    public void setGender(Boolean gender) {
        this.gender = gender;
    }
    
    public Double getSalary() {
        return salary;
    }
    
    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
