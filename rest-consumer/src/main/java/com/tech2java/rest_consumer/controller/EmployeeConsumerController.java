package com.tech2java.rest_consumer.controller;


import com.tech2java.rest_consumer.entity.Employee;
import com.tech2java.rest_consumer.proxy.EmployeeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeConsumerController {

    @Autowired
    private EmployeeProxy employeeProxy;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    //http://localhost:8081/getEmployee?name=Cool
    @GetMapping("/getEmployee")
    public Employee retrieveEmployeeByName(@RequestParam("name") String name){

        System.out.println("Inside EmployeeConsumerController::retrieveEmployeeByName method.");
        return employeeProxy.retrieveEmployeeByName(name);
    }


    //http://localhost:8081/saveEmp
     //Request Body
    /**
    {
     "empName":"Hal",
     "empAge":38
     }
    */

    @PostMapping("/saveEmp")
    public ResponseEntity<Employee> saveEmp(@RequestBody Employee employee){

        String uri="http://localhost:9090/emp/api/save";

        HttpHeaders headers=new HttpHeaders();
        headers.add("invocationForm","restTemplate");
        HttpEntity<Employee> httpEntity=new HttpEntity<>(employee,headers);

        ResponseEntity<Employee> responseEntity=restTemplate.exchange(uri, HttpMethod.POST,httpEntity,Employee.class);
        //last parameter - Response after calling exchange() method.
        return responseEntity;

    }

    @PostMapping("/saveEmployee")
    public Mono<Employee> saveEmployee(@RequestBody Employee employee){

        System.out.println("Inside saveEmployee method: using WebClient:::");
        String uri="http://localhost:9090/emp/api/save";
        return webClient.post().uri(uri)
                .body(Mono.just(employee),Employee.class)
                .header("invocationForm","webClinet")
                .retrieve()
                .bodyToMono(Employee.class);
    }

}
