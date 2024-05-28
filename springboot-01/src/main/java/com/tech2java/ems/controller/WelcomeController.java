package com.tech2java.ems.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WelcomeController {

    //@RequestMapping("/welcome")
    @RequestMapping(value = {"/","/welcome","/demo"})
    public String sayHello(){

        System.out.println("Inside WelcomeController::sayHello() method");
        return "hello.html";
    }
}
