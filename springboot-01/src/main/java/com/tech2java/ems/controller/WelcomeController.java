package com.tech2java.ems.controller;


import com.tech2java.ems.util.EMSProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class WelcomeController {

    @Value("${ems.address}")
    private String emsAddress;

    @Autowired
    private Environment environment;

    @Autowired
    private EMSProperties emsProperties;

    @Value("${message1}")
    private String message1;

    @Value("${ems.database.uname}")
    private String uname;


    //@RequestMapping("/welcome")
    @RequestMapping(value = {"welcome","demo"})
    public String sayHello(){

        System.out.println("Inside WelcomeController::sayHello() method");
        System.out.println("emsAddress===>"+emsAddress);

        System.out.println("ems.address::"+environment.getProperty("ems.address"));
        System.out.println("JAVA_HOME::"+environment.getProperty("JAVA_HOME"));


        System.out.println("Address::"+emsProperties.getAddress());
        System.out.println("Contact::"+emsProperties.getContact());
        System.out.println("branches::"+emsProperties.getBranches());

        System.out.println("message1::"+message1);

        System.out.println("message2::"+environment.getProperty("message2"));


        System.out.println("UAT properties loading:");

        System.out.println("ems.database.uname::"+uname);


        printLoggers();
        return "hello.html";
    }

    private void printLoggers() {

        log.error("Log error message");
        log.warn("log warn message");
        log.info("log info message");
        log.debug("log debug message");
        log.trace("log trace message");

    }
}
