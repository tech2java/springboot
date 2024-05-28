package com.tech2java.ems.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")

    //@Min(value = 3,message = "Employee number should be minimum 3 digits")
    @Column(name = "emp_no")
    private int empNo;

    @NotBlank(message = "Employee name should not be empty")
    //@JsonProperty("EmployeeName")
    @Column(name = "emp_name")
    private String empName;

    //@JsonIgnore
    @Column(name = "emp_age")
    private int empAge;

}
