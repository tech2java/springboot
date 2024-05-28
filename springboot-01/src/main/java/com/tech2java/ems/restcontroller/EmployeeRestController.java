package com.tech2java.ems.restcontroller;

import com.tech2java.ems.entity.Employee;
import com.tech2java.ems.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/emp/api/")
public class EmployeeRestController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/save")
    public Employee save(@RequestBody Employee employee){


      //Testing Derived methods

        /*Employee employee1=employeeRepository.findByEmpAge(45);
        Employee employee2=employeeRepository.queryByEmpName("Cool");
        Employee employee3=employeeRepository.getByEmpNameAndEmpAge("Srinivas",38);

        System.out.println("KKKK==>"+employee1);
        log.info(employee1.getEmpName());
        log.info(employee2.getEmpNo()+"");
        log.info(employee3.getEmpAge()+"");*/

      log.info("Inside EmployeeRestController::save() method::"+employee);
      return  employeeRepository.save(employee);

    }
}
