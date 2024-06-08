package com.tech2java.rest_consumer.entity;


import lombok.Data;


@Data
public class Employee extends BaseEntity{

    private int empNo;

    private String empName;

    private int empAge;

}
