package com.tech2java.ems.restcontroller;

import com.tech2java.ems.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeRestControllerMVCStyle {

    @RequestMapping("/saveEmp")
    @ResponseBody
    public String save(){
        System.out.println("EmployeeRestControllerMVCStyle::save()::");
        return "Employee saved Successfully.";
    }





}
