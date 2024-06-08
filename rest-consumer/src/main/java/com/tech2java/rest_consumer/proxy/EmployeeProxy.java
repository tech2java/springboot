package com.tech2java.rest_consumer.proxy;

import com.tech2java.rest_consumer.config.ProjectConfig;
import com.tech2java.rest_consumer.entity.Employee;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="employee" ,url = "http://localhost:9090/emp/api",configuration = ProjectConfig.class)
public interface EmployeeProxy {


    @RequestMapping(method = RequestMethod.GET,value = "/retrieve")
    @Headers(value = "Content-Type:application/json")//optional
    public Employee retrieveEmployeeByName(@RequestParam("name") String name);
}
