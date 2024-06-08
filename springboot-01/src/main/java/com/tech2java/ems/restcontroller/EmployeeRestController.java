package com.tech2java.ems.restcontroller;

import com.tech2java.ems.entity.Employee;
import com.tech2java.ems.repository.EmployeeRepository;
import jakarta.persistence.GeneratedValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/retrieve")
    public Employee retrieveEmployeeByName(@RequestParam("name") String name){
        log.info("Inside retrieveEmployeeByName -- request param...");
        Employee employee=employeeRepository.retrieveEmployeeByName(name);
        System.out.println(employee);
        return employee;
    }

    //http://localhost:9090/emp/api/retrieve/Cool
    @GetMapping("/retrieve/{name}")
    public Employee retrieveEmployeeByName1(@PathVariable("name") String name){
        log.info("Inside retrieveEmployeeByName1 -- path variable...");
        Employee employee1=employeeRepository.retrieveEmployeeByName2(name);
        System.out.println("JPQL named param result::"+employee1);


        Employee employee2=employeeRepository.retrieveEmployeeByNameNative(name);
        System.out.println("Native Query result::"+employee2);


        return employee1;
    }

    //http://localhost:9090/emp/api/update?age=33&empId=8
    @PutMapping("/update")
    public int updateEmployee(@RequestParam("age") int age,@RequestParam("empId") int empId){
        log.info("Inside updateEmployee --method...");
        return employeeRepository.updateEmployeeAge(age,empId);
    }
}
