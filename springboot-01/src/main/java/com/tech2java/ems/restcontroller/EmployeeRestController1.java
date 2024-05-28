package com.tech2java.ems.restcontroller;

import com.tech2java.ems.entity.Employee;
import com.tech2java.ems.pojo.Response;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

//@RestController // @Controller+@ResponeBody
//@RequestMapping(value = "/employee/api",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
public class EmployeeRestController1 {

    //http://localhost:9090/springboot01/employee/api/save
    @GetMapping("/save")
    public String save(){
        System.out.println("EmployeeRestController::save()");
        return "Employee saved Successfully.";
    }

    //http://localhost:9090/springboot01/employee/api/get/102
    @GetMapping("/get/{empNo}")
    public String get(@PathVariable String empNo){
        System.out.println("EmployeeRestController::get() path variable::empNo::"+empNo);
        return "Employee getting Successfully.";
    }

    //http://localhost:9090/springboot01/employee/api/get?empNo=102
    @GetMapping("/get")
    public String retrieve(@RequestParam("empNo") String empNo){
        System.out.println("EmployeeRestController::retrieve()::request param::empNo::"+empNo);
        return "Employee retrieved Successfully.";
    }

    /**
     * Request:
     * {
     *     "empNo":100,
     *     "empAge":39,
     *     "empName":"Srinivas"
     * }
     *
     * http://localhost:9090/springboot01/employee/api/save1
     *
     *
     * @param employee
     * @return
     */
    @PostMapping("/save1")
    @ResponseBody
    public Employee save1(@RequestBody Employee employee){
        System.out.println("employee name::"+employee.getEmpName());
        System.out.println("EmployeeRestControllerMVCStyle::save()::" +employee);
        return employee;
    }


    //http://localhost:9090/springboot01/employee/api/save2

    /**
     *
     * Request:
     * {
     *     "empNo":100,
     *     "empAge":39,
     *     "empName":"Srinivas"
     * }
     *
     * Response:
     * {
     *     "statusCode": "200",
     *     "statusMessage": "Employee saved Successfully."
     * }
     * add headers before sending request
     */

    //RequestEntity & ResponseEntity
    @PostMapping("/save2")
    public ResponseEntity<Response> save2(RequestEntity<Employee> requestEntity){


        //Retrieving headers
        HttpHeaders headers =requestEntity.getHeaders();
        System.out.println("request header::"+headers);
        //Retrieving body
        Employee employee=requestEntity.getBody();

        System.out.println("Employee::"+employee);

        Response response=new Response();
        response.setStatusCode("200");
        response.setStatusMessage("Employee saved Successfully.");

        return  ResponseEntity
                .status(HttpStatus.OK)
                .header("testHeader","testHeaderValue")
                .body(response);
    }

    @PostMapping("/save3")
    @ResponseBody
    public Employee save3(@Valid @RequestBody Employee employee){

        return  employee;
    }
}
